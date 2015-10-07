USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[CategoryCreate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[CategoryCreate]
GO

/****** Object:  StoredProcedure [dbo].[CategoryCreate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[CategoryCreate]

	@categoryname		NVARCHAR(10) = NULL
,	@iconfilename		VARCHAR(255) = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
    DECLARE @categorysn TINYINT
    SELECT @categorysn = categorysn FROM Category ORDER BY categorysn DESC
    
    IF @categorysn IS NULL
		BEGIN
			SET @categorysn = 1
		END
    ELSE
		BEGIN
			SET @categorysn = @categorysn + 1
		END
    
	INSERT INTO dbo.Category
	(
		categorysn
	,	categoryname
	,	iconfilename
	)
	VALUES
	(
		@categorysn
	,	@categoryname
	,	@iconfilename
	)
	
END

GO


