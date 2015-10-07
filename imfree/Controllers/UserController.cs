using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Data.Objects;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;

using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

using imfree.Lib;
using imfree.Models;

namespace imfree.Controllers
{
    public class UserController : ApiController
    {
        IMFREEEntities entity = new IMFREEEntities();

        ReturnValue returnValue = new ReturnValue();

        #region Auth
        public ReturnValue auth([FromBody]RequestValue value)
        {
            if(value == null)
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

                Users user = new Users();
                user.usersn = (long)userSN.Value;
                user.guid = (string)gUID.Value;
                user.hashphone = (string)hashPhone.Value;
                user.deviceid = (string)deviceID.Value;
                user.pushkey = (string)pushKey.Value;
                user.createdate = (DateTime)createDate.Value;
                user.updatedate = (DateTime)updateDate.Value;

                string strGUID = string.Empty;

                if ((string)hashPhone.Value == (string)value.data.hashphone)
                {
                    strGUID = Guid.NewGuid().ToString().Replace("-", string.Empty);

                    returnValue.error = 0;
                    returnValue.message = "OK";

                    string guid = strGUID;
                    long usersn = (long)userSN.Value;
                    returnValue.data = new
                    {
                        usersn,
                        guid
                    };
                }
                else
                {
                    returnValue.error = 101;
                    returnValue.message = "USER_INVALID_PHONE";
                }

                // guid 를 갱신 한다.
                entity.UserUpdate(value.token, strGUID, null, null, null);

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

        #region create
        public ReturnValue create([FromBody]RequestValue value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }

            CreateUser createUser = JsonConvert.DeserializeObject<CreateUser>(Convert.ToString(value.data));
            string strGUID = Guid.NewGuid().ToString().Replace("-", string.Empty);
            
#if DEBUG
            if (value.token.Equals("A1234567890")) strGUID = "guid1";
            if (value.token.Equals("B1234567890")) strGUID = "guid2";
            if (value.token.Equals("C1234567890")) strGUID = "guid3";
            if (value.token.Equals("D1234567890")) strGUID = "guid4";
            if (value.token.Equals("E1234567890")) strGUID = "guid5";
#endif
            try
            {
                ObjectParameter userSN = new ObjectParameter("UserSN", typeof(long));

                // 사용자 정보 등록
                entity.UserCreate(userSN, value.token, strGUID, createUser.user.hashphone, createUser.user.deviceid, createUser.user.pushkey);

                /*
                ObjectParameter userSN = new ObjectParameter("UserSN", typeof(long));
                ObjectParameter gUID = new ObjectParameter("GUID", typeof(string));
                ObjectParameter hashPhone = new ObjectParameter("HashPhone", typeof(string));
                ObjectParameter deviceID = new ObjectParameter("DeviceID", typeof(string));
                ObjectParameter pushKey = new ObjectParameter("PushKey", typeof(string));
                ObjectParameter createDate = new ObjectParameter("CreateDate", typeof(DateTime));
                ObjectParameter updateDate = new ObjectParameter("UpdateDate", typeof(DateTime));

                // User SN 정보 조회
                entity.UserGetInfoByToken(value.token, userSN, gUID, hashPhone, deviceID, pushKey, createDate, updateDate);
                */

                returnValue.error = 0;
                returnValue.message = "ok";

                string guid = strGUID;
                long usersn = (long)userSN.Value;
                returnValue.data = new
                {
                    usersn,
                    guid
                };
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

    public class CreateUser
    {
        public UserInfo user { get; set; }
    }

    public class UserInfo
    {
        public string hashphone { get; set; }
        public string deviceid { get; set; }
        public string pushkey { get; set; }
    }
}
