USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[SuggestCreate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[SuggestCreate]
GO

/****** Object:  StoredProcedure [dbo].[SuggestCreate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[SuggestCreate]

	@suggestsn		BIGINT = NULL OUTPUT
	
,	@usersn			BIGINT = NULL
,	@hashphone		VARCHAR(512) = NULL
,	@categorysn		TINYINT = NULL
,	@itemsn			TINYINT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
	INSERT INTO dbo.Suggest
	(
		usersn
	,	hashphone
	,	createdate
	,	updatedate
	,	categorysn
	,	itemsn
	,	acceptedusercount
	,	cancelled
	)
	VALUES
	(
		@usersn
	,	@hashphone
	,	GETDATE()
	,	GETDATE()
	,	@categorysn
	,	@itemsn
	,	0
	,	0
	)
	
	SET @suggestsn = @@IDENTITY
	
END

GO


