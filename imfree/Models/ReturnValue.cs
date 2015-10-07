using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace imfree.Models
{
    public class ReturnValue
    {
        /// <summary>
        /// 200 ok 이지만 로직 에러인 경우 사용을 하게 된다.
        /// 로직 에러는 범위를 지정하여 사용한다.
        /// 범위는 공통 범위와 API 서비스에 해당 하는 부분으로 구분 한다.
        /// </summary>
        public int error = 0;

        /// <summary>
        /// 에러 메시지
        /// </summary>
        public string message = "ok";

        /// <summary>
        /// 결과 JSOM TYPE
        /// </summary>
        public dynamic data;
    }
}