USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[CustomReportGetList]    Script Date: 2015. 6. 2. ���� 8:34:33 ******/
DROP PROCEDURE [dbo].[CustomReportGetList]
GO

/****** Object:  StoredProcedure [dbo].[CustomReportGetList]    Script Date: 2015. 6. 2. ���� 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[CustomReportGetList]


AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
	SELECT TOP 50
		reportsn
	,	report
	FROM
		dbo.CustomReport
	ORDER BY
		reportsn DESC
END




GO


