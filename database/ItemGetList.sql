USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[ItemGetList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[ItemGetList]
GO

/****** Object:  StoredProcedure [dbo].[ItemGetList]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ItemGetList]

@categorysn	TINYINT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
	IF @categorysn IS NOT NULL
		BEGIN
		SELECT
			categorysn
		,	itemsn
		,	itemname
		,	cardfilename
		FROM
			dbo.Item WITH(NOLOCK)
		WHERE
			categorysn = @categorysn
		END
	ELSE
		BEGIN
		SELECT
			categorysn
		,	itemsn
		,	itemname
		,	cardfilename
		FROM
			dbo.Item WITH(NOLOCK)
		END
END




GO


