using System;
using System.Collections.Generic;
using System.Data.Objects;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;

using imfree.Models;

namespace imfree.Controllers
{
    public class InstallUsersController : ApiController
    {
        // GET api/installusers
        public ReturnValue Get()
        {
            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            return returnValue;
        }

        // GET api/installusers/5
        public ReturnValue Get(int id)
        {
            IMFREEEntities entity = new IMFREEEntities();

            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            try
            {
                ObjectParameter TotalCount = new ObjectParameter("TotalCount", typeof(int));
                returnValue.totalcount = (int)TotalCount.Value;
                returnValue.value = entity.InstallUserGetList(id, TotalCount).ToList<InstallUserGetList_Result>();
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // GET api/installusers?UserSN=1&UserSN_Object=2
        public ReturnValue Get(int id, int usersn_object)
        {
            IMFREEEntities entity = new IMFREEEntities();

            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            try
            {
                InstallUserGetList_Result value = new InstallUserGetList_Result();
                int result = entity.InstallUserGetInfo(id, usersn_object);

                if (result == 0)
                {
                    value.UserSN = id;
                    value.UserSN_Object = usersn_object;
                }

                returnValue.value = value;
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // POST api/installusers
        public ReturnValue Post([FromBody]InstallUsers value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            IMFREEEntities entity = new IMFREEEntities();
            try
            {
                entity.InstallUserCreate(value.UserSN, value.UserSN_Object);
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            return returnValue;
        }

        // PUT api/installusers/5
        public ReturnValue Put(int id, [FromBody]InstallUsers value)
        {
            if (id < 0 || value == null)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }

            IMFREEEntities entity = new IMFREEEntities();
            try
            {
                if (id == value.UserSN)
                {
                    entity.InstallUserUpdate(value.UserSN, value.UserSN_Object);
                }
                else
                {
                    throw new HttpResponseException(HttpStatusCode.BadRequest);
                }
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            return returnValue;
        }

        // DELETE api/installusers/5
        public ReturnValue Delete(int id)
        {
            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            return returnValue;
        }
    }
}
