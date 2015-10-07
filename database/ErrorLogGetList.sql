USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[ErrorLogGetList]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[ErrorLogGetList]
GO

/****** Object:  StoredProcedure [dbo].[ErrorLogGetList]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ErrorLogGetList]

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


    -- Insert statements for procedure here
    
	SELECT
	TOP 50
		logsn
	,	ip
	,	uri
	,	source
	,	method
	,	dtcreate
	,	error
	,	trace
	,	json
	FROM
		dbo.ErrorLog
	ORDER BY
		logsn DESC
END



GO


