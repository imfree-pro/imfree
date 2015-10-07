USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[CategoryUpdate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[CategoryUpdate]
GO

/****** Object:  StoredProcedure [dbo].[CategoryUpdate]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[CategoryUpdate]

	@categorysn			TINYINT = NULL
	
,	@categoryname		NVARCHAR(10) = NULL
,	@iconfilename		VARCHAR(255) = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
    UPDATE dbo.Category
	SET
		categoryname = ISNULL(@categoryname, categoryname)
	,	iconfilename = ISNULL(@iconfilename, iconfilename)
	WHERE
		categorysn = @categorysn
	
END

GO


