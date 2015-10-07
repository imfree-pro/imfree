using System;
using System.Collections.Generic;
using System.Data.Objects;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

using imfree.Lib;
using imfree.Models;

namespace imfree.Controllers
{
    public class ContactsController : ApiController
    {
        
        IMFREEEntities entity = new IMFREEEntities();

        ReturnValue returnValue = new ReturnValue();

        #region create
        public ReturnValue create([FromBody]RequestValue value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }

            try
            {
                // User SN 정보 조회
                ObjectParameter userSN = new ObjectParameter("UserSN", typeof(long));
                ObjectParameter gUID = new ObjectParameter("GUID", typeof(string));
                ObjectParameter hashPhone = new ObjectParameter("HashPhone", typeof(string));
                ObjectParameter deviceID = new ObjectParameter("DeviceID", typeof(string));
                ObjectParameter pushKey = new ObjectParameter("PushKey", typeof(string));
                ObjectParameter createDate = new ObjectParameter("CreateDate", typeof(DateTime));
                ObjectParameter updateDate = new ObjectParameter("UpdateDate", typeof(DateTime));

                entity.UserGetInfoByToken(value.token, userSN, gUID, hashPhone, deviceID, pushKey, createDate, updateDate);

                // 저장되 있는 연락처 정보와 업데이트 연락처 정보를 머징.
                ContactsRequest syncdata = JsonConvert.DeserializeObject<ContactsRequest>(Convert.ToString(value.data));

                foreach (string hashphone in syncdata.contacts)
                {
                    entity.ContactsCreate((long)userSN.Value, hashphone);
                }

                returnValue.error = 0;
                returnValue.message = "OK";
            }
            catch (InvalidCastException)
            {
                returnValue.error = 100;
                returnValue.message = "USER_NOT_FOUND";
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value)); ;
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
        #endregion

        #region remove
        public ReturnValue remove([FromBody]RequestValue value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }

            try
            {
                // User SN 정보 조회
                ObjectParameter userSN = new ObjectParameter("UserSN", typeof(long));
                ObjectParameter gUID = new ObjectParameter("GUID", typeof(string));
                ObjectParameter hashPhone = new ObjectParameter("HashPhone", typeof(string));
                ObjectParameter deviceID = new ObjectParameter("DeviceID", typeof(string));
                ObjectParameter pushKey = new ObjectParameter("PushKey", typeof(string));
                ObjectParameter createDate = new ObjectParameter("CreateDate", typeof(DateTime));
                ObjectParameter updateDate = new ObjectParameter("UpdateDate", typeof(DateTime));

                entity.UserGetInfoByToken(value.token, userSN, gUID, hashPhone, deviceID, pushKey, createDate, updateDate);

                // 저장되 있는 연락처 정보와 업데이트 연락처 정보를 머징.
                ContactsRequest syncdata = JsonConvert.DeserializeObject<ContactsRequest>(Convert.ToString(value.data));

                foreach (string hashphone in syncdata.contacts)
                {
                    entity.ContactsDelete((long)userSN.Value, hashphone);
                }

                returnValue.error = 0;
                returnValue.message = "OK";
            }
            catch (InvalidCastException)
            {
                returnValue.error = 100;
                returnValue.message = "USER_NOT_FOUND";
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value)); ;
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
        #endregion

        #region installuserlist
        public ReturnValue installuserlist([FromBody]RequestValue value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }

            try
            {
                ObjectParameter userSN = new ObjectParameter("UserSN", typeof(long));
                ObjectParameter gUID = new ObjectParameter("GUID", typeof(string));
                ObjectParameter hashPhone = new ObjectParameter("HashPhone", typeof(string));
                ObjectParameter deviceID = new ObjectParameter("DeviceID", typeof(string));
                ObjectParameter pushKey = new ObjectParameter("PushKey", typeof(string));
                ObjectParameter createDate = new ObjectParameter("CreateDate", typeof(DateTime));
                ObjectParameter updateDate = new ObjectParameter("UpdateDate", typeof(DateTime));

                // User SN 정보 조회
                entity.UserGetInfoByToken(value.token, userSN, gUID, hashPhone, deviceID, pushKey, createDate, updateDate);

                returnValue.error = 0;
                returnValue.message = "ok";

                ObjectParameter totalCount = new ObjectParameter("TotalCount", typeof(int));
                List<string> installusers = entity.ContactsInstallUserList((long)userSN.Value, totalCount).ToList<string>();

                returnValue.data = new
                {
                    totalcount = (int)totalCount.Value,
                    installusers
                };
            }
            catch (InvalidCastException)
            {
                returnValue.error = 100;
                returnValue.message = "USER_NOT_FOUND";
                returnValue.data = null;
            }
            catch(Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value)); ;
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
        #endregion
    }

    public class ContactsRequest
    {
        public List<string> contacts { get; set; }
    }
}
