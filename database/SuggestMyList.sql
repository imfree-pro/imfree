USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[SuggestMyList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[SuggestMyList]
GO

/****** Object:  StoredProcedure [dbo].[SuggestMyList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[SuggestMyList]

	@usersn		BIGINT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
END

	SELECT
		suggestsn
	,	usersn
	,	createdate
	,	updatedate
	,	categorysn
	,	itemsn
	,	acceptedusercount
	FROM
		dbo.Suggest
	WHERE
		cancelled = 0 AND usersn = @usersn AND createdate > CONVERT(CHAR(10), GETDATE(), 120) AND createdate < CONVERT(CHAR(10), GETDATE()+1, 120)


GO
