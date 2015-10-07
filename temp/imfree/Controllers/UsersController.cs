using System;
using System.Collections.Generic;
using System.Data.Objects;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

using imfree.Models;

namespace imfree.Controllers
{
    public class UsersController : ApiController
    {

        IMFREEEntities entity = new IMFREEEntities();

        // GET api/users
        public ReturnValue Get()
        {
            ObjectParameter totalCount = new ObjectParameter("TotalCount", typeof(int));

            ReturnValue returnValue = new ReturnValue();

            try
            {
                returnValue.value = entity.UserGetList(totalCount).ToList<UserGetList_Result>();
                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // GET api/users/5
        public ReturnValue Get(int id)
        {
            if (id < 0)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ObjectParameter phoneHash = new  ObjectParameter("PhoneHash", typeof(string));
            ObjectParameter createDate = new ObjectParameter("CreateDate", typeof(DateTime)); ;
            ObjectParameter updateDate = new ObjectParameter("UpdateDate", typeof(DateTime)); ;
            ObjectParameter pushKey = new ObjectParameter("PushKey", typeof(string)); ;
            ObjectParameter deviceID = new ObjectParameter("DeviceID", typeof(string)); ;

            ReturnValue returnValue = new ReturnValue();

            try
            {
                entity.UserGetInfo(id, phoneHash, createDate, updateDate, pushKey, deviceID);

                returnValue.code = 0;
                returnValue.msg = "succes";

                Users users = new Users();
                users.UserSN = id;
                users.PhoneHash = (string)phoneHash.Value;
                users.CreateDate = (DateTime)createDate.Value;
                users.UpdateDate = (DateTime)updateDate.Value;
                users.PushKey = (string)pushKey.Value;
                users.DeviceID = (string)deviceID.Value;

                returnValue.value = users;
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // POST api/users
        public ReturnValue Post([FromBody]Users value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ReturnValue returnValue = new ReturnValue();
            try
            {
                entity.UserCreate(value.PhoneHash, value.PushKey, value.DeviceID);

                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // PUT api/users/5
        public ReturnValue Put(int id, [FromBody]Users value)
        {
            if (id < 0 || value == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            if (id != value.UserSN)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }

            ReturnValue returnValue = new ReturnValue();
            try
            {
                entity.UserUpdate(id, value.PhoneHash, value.PushKey, value.DeviceID);

                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // DELETE api/users/5
        public ReturnValue Delete(int id)
        {
            if (id < 0)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ReturnValue returnValue = new ReturnValue();

            try
            {
                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
    }
}
