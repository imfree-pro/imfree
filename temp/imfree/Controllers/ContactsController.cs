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
    public class ContactsController : ApiController
    {
        IMFREEEntities entity = new IMFREEEntities();

        // GET api/contacts
        /// <summary>
        /// 전체 연락처 정보를 조회하는 API 로 지원 하지 않는다.
        /// </summary>
        /// <returns></returns>
        public ReturnValue Get()
        {
            ReturnValue returnValue = new ReturnValue();
            returnValue.code = 0;
            returnValue.msg = "succes";


            return returnValue;
        }

        // GET api/contacts/5
        /// <summary>
        /// usersn에 해당 하는 정보를 조회한다.
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public ReturnValue Get(int id)
        {
            if (id < 0)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ReturnValue returnValue = new ReturnValue();

            try
            {
                returnValue.value = entity.ContactGetInfo(id).ToList<ContactGetInfo_Result>();
                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // POST api/contacts
        public ReturnValue Post([FromBody]Contacts value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ReturnValue returnValue = new ReturnValue();

            try
            {
                returnValue.value = entity.ContactCreate(value.UsersSN, value.PhoneHash, value.PhoneName);
                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // PUT api/contacts/5
        public ReturnValue Put(int id, [FromBody]Contacts value)
        {
            if (value == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ReturnValue returnValue = new ReturnValue();

            try
            {
                returnValue.value = entity.ContactUpdate(value.UsersSN, value.PhoneHash, value.PhoneName);
                returnValue.code = 0;
                returnValue.msg = "succes";
            }
            catch
            {
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }

        // DELETE api/contacts/5
        public ReturnValue Delete(int id)
        {
            if (id < 0)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            ReturnValue returnValue = new ReturnValue();

            try
            {
                //returnValue.value = 
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
