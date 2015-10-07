USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[ContactsInstallUserList]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[ContactsInstallUserList]
GO

/****** Object:  StoredProcedure [dbo].[ContactsInstallUserList]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ContactsInstallUserList]

	@usersn		BIGINT = NULL
	
,	@TotalCount	INT = NULL OUTPUT

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
	SELECT
		U.hashphone
	FROM
		dbo.Users U WITH(NOLOCK) INNER JOIN (SELECT hashphone FROM dbo.Contacts WITH(NOLOCK) WHERE usersn = @usersn) C
	ON
		U.hashphone = C.hashphone
		
	SELECT
		@TotalCount = COUNT(U.hashphone)
	FROM
		dbo.Users U WITH(NOLOCK) INNER JOIN (SELECT hashphone FROM dbo.Contacts WITH(NOLOCK) WHERE usersn = @usersn) C
	ON
		U.hashphone = C.hashphone
END

GO


