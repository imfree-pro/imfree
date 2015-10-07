USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[SuggestDelete]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
DROP PROCEDURE [dbo].[SuggestDelete]
GO

/****** Object:  StoredProcedure [dbo].[SuggestDelete]    Script Date: 2015. 6. 2. 오후 8:32:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[SuggestDelete]

	
	@usersn			BIGINT = NULL
,	@suggestsn		BIGINT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
	UPDATE dbo.Suggest
	SET
		cancelled = ISNULL(1, cancelled)
	WHERE
		usersn = @usersn AND suggestsn = @suggestsn
END

GO


