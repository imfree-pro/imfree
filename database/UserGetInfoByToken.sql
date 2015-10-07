USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[UserGetInfoByToken]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[UserGetInfoByToken]
GO

/****** Object:  StoredProcedure [dbo].[UserGetInfoByToken]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserGetInfoByToken]

	@accesstoken	VARCHAR(255) = NULL

,	@usersn			BIGINT = NULL OUTPUT
,	@guid			CHAR(32) = NULL OUTPUT
,	@hashphone		VARCHAR(512) = NULL OUTPUT
,	@deviceid		CHAR(16) = NULL OUTPUT
,	@pushkey		VARCHAR(256) = NULL OUTPUT
,	@createdate	DATETIME = NULL OUTPUT
,	@updatedate		DATETIME = NULL OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
	SELECT
		@usersn = usersn
	,	@guid = guid
	,	@hashphone = hashphone
	,	@deviceid = deviceid
	,	@pushkey = pushkey
	,	@createdate = createdate
	,	@updatedate = updatedate
	FROM
		dbo.Users WITH(NOLOCK)
	WHERE
		accesstoken = @accesstoken
END




GO


