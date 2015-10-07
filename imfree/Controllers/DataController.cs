using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Data;
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
    public class DataController : ApiController
    {   
        IMFREEEntities entity = new IMFREEEntities();
        ReturnValue returnValue = new ReturnValue();

        #region sync
        public ReturnValue sync([FromBody]RequestValue value)
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

                DataTable newContacts = BulkCopy.MakeTable();

                foreach (string hashphone in syncdata.contacts)
                {
                    //entity.ContactsCreate((long)userSN.Value, hashphone);
                    DataRow row = newContacts.NewRow();
                    row["usersn"] = (long)userSN.Value;
                    row["hashphone"] = hashphone;

                    newContacts.Rows.Add(row);
                }

                newContacts.AcceptChanges();

                DataRow[] rowArray = newContacts.Select();

                BulkCopy bc = new BulkCopy();
                bc.copy(rowArray);

                returnValue.error = 0;
                returnValue.message = "OK";

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
            }
            catch (Exception ex)
            {
                entity.ErrorLogCreate(HttpRequestMessageHelper.GetClientIpAddress(Request), Request.RequestUri.AbsoluteUri, ex.Source, ex.TargetSite.Name, ex.Message, ex.StackTrace, JsonConvert.SerializeObject(value)); ;
                throw new HttpResponseException(HttpStatusCode.InternalServerError);
            }

            return returnValue;
        }
        #endregion
    }
}
