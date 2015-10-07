USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[ErrorLogCreate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[ErrorLogCreate]
GO

/****** Object:  StoredProcedure [dbo].[ErrorLogCreate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ErrorLogCreate]

	@ip			VARCHAR(15) = NULL
,	@uri		VARCHAR(1024) = NULL
,	@source		VARCHAR(1024) = NULL
,	@method		VARCHAR(50) = NULL
,	@error		VARCHAR(512) = NULL
,	@trace		TEXT = NULL
,	@json		TEXT = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


    -- Insert statements for procedure here
    
	INSERT INTO dbo.ErrorLog
	(
		ip
	,	uri
	,	source
	,	method
	,	dtcreate
	,	error
	,	trace
	,	json
	)
	VALUES
	(
		@ip
	,	@uri
	,	@source
	,	@method
	,	GETDATE()
	,	@error
	,	@trace
	,	@json
	)
END



GO


