USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[CustomReportCreate]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[CustomReportCreate]
GO

/****** Object:  StoredProcedure [dbo].[CustomReportCreate]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[CustomReportCreate]

	@Report		TEXT = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
	INSERT INTO dbo.CustomReport
	(
		report
	)
	VALUES
	(
		@report
	)
	
END




GO


