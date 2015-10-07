USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[ContactsDelete]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[ContactsDelete]
GO

/****** Object:  StoredProcedure [dbo].[ContactsDelete]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ContactsDelete]

	@usersn		BIGINT = NULL
,	@hashphone	VARCHAR(512) = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
	DELETE dbo.Contacts
	WHERE
		usersn = @usersn AND hashphone = @hashphone
	
END

GO


