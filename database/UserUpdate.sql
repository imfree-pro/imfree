USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[UserUpdate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[UserUpdate]
GO

/****** Object:  StoredProcedure [dbo].[UserUpdate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserUpdate]

	@accesstoken	VARCHAR(255) = NULL
,	@guid			CHAR(32) = NULL
,	@hashphone		VARCHAR(512) = NULL
,	@deviceid		CHAR(16) = NULL
,	@pushkey		VARCHAR(256) = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


    -- Insert statements for procedure here
    
	UPDATE dbo.Users
	SET
		guid		= ISNULL(@guid, guid)
	,	hashphone	= ISNULL(@hashphone, hashphone)
	,	deviceid	= ISNULL(@deviceid, deviceid)
	,	pushkey		= ISNULL(@pushkey, pushkey)
	,	updatedate	= GETDATE()
	WHERE
		accesstoken = @accesstoken
END



GO


