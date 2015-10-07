USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[CategoryGetList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[CategoryGetList]
GO

/****** Object:  StoredProcedure [dbo].[CategoryGetList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[CategoryGetList]

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
	SELECT
		categorysn
	,	categoryname
	,	iconfilename
	FROM
		dbo.Category WITH(NOLOCK)
END




GO


