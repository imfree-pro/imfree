using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

using Microsoft.SqlServer.Management.Smo;
using Microsoft.SqlServer.Server;
using Microsoft.SqlServer.Management.Common;
using Microsoft.SqlServer;

namespace imfree.Lib
{
    public class BulkCopy
    {
        private string connectionString = "Data Source=54.64.104.143; User id=pinkman; Password=dkfmaekqek; Initial Catalog=IMFREE;";
        private string tableName = "dbo.Contacts";

        public BulkCopy()
        {
        }

        public void copy(DataRow[] rowArray)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();
                using (SqlBulkCopy bulkCopy = new SqlBulkCopy(connection))
                {
                    bulkCopy.DestinationTableName = tableName;

                    try
                    {
                        bulkCopy.WriteToServer(rowArray);
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine(ex.Message);
                    }
                }
            }
        }

        public static DataTable MakeTable()
        {
            DataTable newContacts = new DataTable("Contacts");

            // Add three column objects to the table. 
            DataColumn userSN = new DataColumn();
            userSN.DataType = System.Type.GetType("System.Int64");
            userSN.ColumnName = "usersn";
            userSN.AutoIncrement = true;
            newContacts.Columns.Add(userSN);

            DataColumn hashPhone = new DataColumn();
            hashPhone.DataType = System.Type.GetType("System.String");
            hashPhone.ColumnName = "hashphone";
            newContacts.Columns.Add(hashPhone);

            // Return the new DataTable. 
            return newContacts;
        }
    }
}