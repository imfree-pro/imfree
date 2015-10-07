USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[ItemCreate]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[ItemCreate]
GO

/****** Object:  StoredProcedure [dbo].[ItemCreate]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ItemCreate]

	@categorysn		TINYINT = NULL
,	@itemname		NVARCHAR(50) = NULL
,	@cardfilename	VARCHAR(255) = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
	DECLARE @itemsn TINYINT
    SELECT @itemsn = itemsn FROM dbo.Item WHERE categorysn = @categorysn ORDER BY categorysn DESC
	
	INSERT INTO dbo.Item
	(
		categorysn
	,	itemsn
	,	itemname
	,	cardfilename
	)
	VALUES
	(
		@categorysn
	,	@itemsn
	,	@itemname
	,	@cardfilename
	)
	
END




GO


