USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[SuggestFriendList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[SuggestFriendList]
GO

/****** Object:  StoredProcedure [dbo].[SuggestFriendList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[SuggestFriendList]

	@usersn		BIGINT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
END

	SELECT
		S.suggestsn
	,	S.usersn
	,	S.createdate
	,	S.updatedate
	,	S.categorysn
	,	S.itemsn
	,	S.acceptedusercount
	,	S.cancelled
	FROM
		dbo.Suggest S WITH(NOLOCK) INNER JOIN
		(
			SELECT
				U.usersn
			FROM
				dbo.Users U WITH(NOLOCK) INNER JOIN (SELECT hashphone FROM dbo.Contacts WITH(NOLOCK) WHERE usersn = @usersn) C
			ON
				U.hashphone = C.hashphone
		) I
	ON
		S.usersn = I.usersn
	WHERE
		createdate > CONVERT(CHAR(10), GETDATE(), 120) AND createdate < CONVERT(CHAR(10), GETDATE()+1, 120)
GO
