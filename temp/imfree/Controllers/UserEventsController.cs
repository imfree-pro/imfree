using System;
using System.Collections.Generic;
using System.Data.Objects;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Web.Script.Serialization;
using System.Web.Http;

using imfree.Models;

namespace imfree.Controllers
{
    public class UserEventsController : ApiController
    {
        IMFREEEntities entity = new IMFREEEntities();

        // GET api/userevents
        public ReturnValue Get()
        {
            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            return returnValue;
        }

        // GET api/userevents/5
        public ReturnValue Get(int id)
        {
            if (id < 0)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ReturnValue returnValue = new ReturnValue();

            try
            {
                ObjectParameter TotalCount = new ObjectParameter("TotalCount", typeof(int));
                returnValue.value = entity.UserEventGetList(id, TotalCount).ToList<UserEventGetList_Result>();

                returnValue.totalcount = (int)TotalCount.Value;
                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // POST api/userevents
        public ReturnValue Post([FromBody]UserEventCreate value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ReturnValue returnValue = new ReturnValue();

            try
            {
                var serializer = new JavaScriptSerializer();
                var serializedResult = serializer.Serialize(value.PhoneHash);

                returnValue.value = entity.UserEventCreate(value.UserSN, value.Catogory, value.CatogoryName, serializedResult);
                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // PUT api/userevents/5
        public ReturnValue Put(int id, [FromBody]string value)
        {
            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            return returnValue;
        }

        // DELETE api/userevents/5
        public ReturnValue Delete(int id)
        {
            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";

            return returnValue;
        }
    }
}
