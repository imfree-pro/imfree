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
    public class SuggestController : ApiController
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

            SuggestRegister suggestRegister = JsonConvert.DeserializeObject<SuggestRegister>(Convert.ToString(value.data));

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

                // 제안등록
                ObjectParameter suggestSN = new ObjectParameter("SuggestSN", typeof(long));
                entity.SuggestCreate(suggestSN, (long)userSN.Value, (string)hashPhone.Value, suggestRegister.categorysn, suggestRegister.itemsn);

                // 비공개 대상 등록
                foreach (string hashphone in suggestRegister.privateuser)
                {
                    entity.PrivateUserCreate((long)suggestSN.Value, hashphone);
                }

                returnValue.error = 0;
                returnValue.message = "ok";

                long suggestsn = (long)suggestSN.Value;
                returnValue.data = new
                {
                    suggestsn
                };
            }
            catch (InvalidCastException)
            {
                returnValue.error = 100;
                returnValue.message = "USER_NOT_FOUND";
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value));
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

                ObjectParameter userSN = new ObjectParameter("UserSN", typeof(long));
                ObjectParameter gUID = new ObjectParameter("GUID", typeof(string));
                ObjectParameter hashPhone = new ObjectParameter("HashPhone", typeof(string));
                ObjectParameter deviceID = new ObjectParameter("DeviceID", typeof(string));
                ObjectParameter pushKey = new ObjectParameter("PushKey", typeof(string));
                ObjectParameter createDate = new ObjectParameter("CreateDate", typeof(DateTime));
                ObjectParameter updateDate = new ObjectParameter("UpdateDate", typeof(DateTime));

                // User SN 정보 조회
                entity.UserGetInfoByToken(value.token, userSN, gUID, hashPhone, deviceID, pushKey, createDate, updateDate);

                // 제안 삭제
                entity.SuggestDelete((long)userSN.Value, (byte)value.data.categorysn);
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value));
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
        #endregion

        # region mylist
        public ReturnValue mylist([FromBody]RequestValue value)
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
                returnValue.data = entity.SuggestMyList((long)userSN.Value).ToList<SuggestMyList_Result>();
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value));
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
        #endregion

        #region friendlist
        public ReturnValue friendlist([FromBody]RequestValue value)
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
                returnValue.data = entity.SuggestFriendList((long)userSN.Value).ToList<SuggestFriendList_Result>();
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value));
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
        #endregion
    }
}
