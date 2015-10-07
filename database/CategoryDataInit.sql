USE [IMFREE]
GO

/****** Object:  StoredProcedure [dbo].[CategoryDataInit]    Script Date: 2015. 6. 2. ���� 8:34:33 ******/
DROP PROCEDURE [dbo].[CategoryDataInit]
GO

/****** Object:  StoredProcedure [dbo].[CategoryDataInit]    Script Date: 2015. 6. 2. ���� 8:34:33 ******/
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

	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (1, 'ģ��')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (2, '����/������')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (3, '��ȭ')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (4, '��Ƽ')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (5, '����')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (6, '�ڵ���')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (7, '����')
	INSERT INTO dbo.Category (categorysn, categoryname) VALUES (8, '���')

	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (1, 1, 'ģ��')
	
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 1, '��ũ������ ���� Ĩ�ô� ~')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 2, '�����Ϸ� �����?')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 3, '��갡��~')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 4, '������ ��å�Ϸ� �����?')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (2, 5, '�״Ͻ� �� ����~')
	
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (3, 1, '��ȭ')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (4, 1, '��Ƽ')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (5, 1, '����')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (6, 1, '�ڵ���')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (7, 1, '����')
	INSERT INTO dbo.Item (categorysn, itemsn, itemname) VALUES (8, 1, '���')

END


GO


