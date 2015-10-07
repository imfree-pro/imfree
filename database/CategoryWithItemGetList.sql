USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[CategoryWithItemGetList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[CategoryWithItemGetList]
GO

/****** Object:  StoredProcedure [dbo].[CategoryWithItemGetList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[CategoryWithItemGetList]

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
	SELECT
		C.categorysn
	,	C.categoryname
	,	C.iconfilename
	,	I.itemsn
	,	I.itemname
	,	I.cardfilename
	FROM
		dbo.Category C WITH(NOLOCK) INNER JOIN dbo.Item I WITH(NOLOCK)
	ON
		C.categorysn = I.categorysn
		
END




GO


