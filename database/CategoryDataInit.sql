USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[CategoryDataInit]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
DROP PROCEDURE [dbo].[CategoryDataInit]
GO

/****** Object:  StoredProcedure [dbo].[CategoryDataInit]    Script Date: 2015. 6. 2. 오후 8:34:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[CategoryDataInit]

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here

	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (1, '친목')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (2, '레저/스포츠')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (3, '문화')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (4, '뷰티')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (5, '육아')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (6, '자동차')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (7, '교육')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (8, '취미')

	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (1, 1, '친목')
	
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 1, '스크린골프 한판 칩시다 ~')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 2, '낚시하러 갈까요?')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 3, '등산가요~')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 4, '가볍게 산책하러 갈까요?')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 5, '테니스 한 게임~')
	
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (3, 1, '문화')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (4, 1, '뷰티')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (5, 1, '육아')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (6, 1, '자동차')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (7, 1, '교육')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (8, 1, '취미')

END


GO


