USE [master]
GO
/****** Object:  Database [IMFREE]    Script Date: 2015. 3. 17. 오전 1:48:25 ******/
CREATE DATABASE [IMFREE]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'IMFREE', FILENAME = N'D:\RDSDBDATA\DATA\IMFREE.mdf' , SIZE = 3136KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'IMFREE_log', FILENAME = N'D:\RDSDBDATA\DATA\IMFREE_log.ldf' , SIZE = 784KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [IMFREE] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [IMFREE].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [IMFREE] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [IMFREE] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [IMFREE] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [IMFREE] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [IMFREE] SET ARITHABORT OFF 
GO
ALTER DATABASE [IMFREE] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [IMFREE] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [IMFREE] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [IMFREE] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [IMFREE] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [IMFREE] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [IMFREE] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [IMFREE] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [IMFREE] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [IMFREE] SET  ENABLE_BROKER 
GO
ALTER DATABASE [IMFREE] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [IMFREE] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [IMFREE] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [IMFREE] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [IMFREE] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [IMFREE] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [IMFREE] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [IMFREE] SET RECOVERY FULL 
GO
ALTER DATABASE [IMFREE] SET  MULTI_USER 
GO
ALTER DATABASE [IMFREE] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [IMFREE] SET DB_CHAINING OFF 
GO
ALTER DATABASE [IMFREE] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [IMFREE] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [IMFREE]
GO
/****** Object:  User [pinkman]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
CREATE USER [pinkman] FOR LOGIN [pinkman] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [pinkman]
GO
/****** Object:  Table [dbo].[CallUsers]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CallUsers](
	[EventSN] [bigint] NOT NULL,
	[UserSN] [varchar](50) NOT NULL,
 CONSTRAINT [PK_CallUsers] PRIMARY KEY CLUSTERED 
(
	[EventSN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Contacts]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Contacts](
	[UsersSN] [bigint] NOT NULL,
	[PhoneHash] [varchar](50) NOT NULL,
	[PhoneName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Contacts] PRIMARY KEY CLUSTERED 
(
	[UsersSN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Events]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Events](
	[EventsN] [bigint] NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[UpdateDate] [datetime] NOT NULL,
	[Catogory] [tinyint] NOT NULL,
	[CatogoryName] [nvarchar](20) NOT NULL,
	[CallCount] [int] NOT NULL,
	[PhoneHash] [text] NOT NULL,
	[IsDelete] [tinyint] NOT NULL,
 CONSTRAINT [PK_Events] PRIMARY KEY CLUSTERED 
(
	[EventsN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[InstallUsers]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InstallUsers](
	[UserSN] [bigint] NOT NULL,
	[UserSN_Object] [bigint] NOT NULL,
 CONSTRAINT [PK_InstallUsers] PRIMARY KEY CLUSTERED 
(
	[UserSN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserEvents]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserEvents](
	[UserSN] [bigint] NOT NULL,
	[EventsN] [bigint] NOT NULL,
 CONSTRAINT [PK_UserEvents] PRIMARY KEY CLUSTERED 
(
	[UserSN] ASC,
	[EventsN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[UserSN] [bigint] NOT NULL,
	[PhoneHash] [varchar](50) NOT NULL,
	[DeviceID] [varchar](50) NOT NULL,
	[PushKey] [varchar](50) NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[UpdateDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UserSN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_PhoneHash_DeviceID]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_PhoneHash_DeviceID] ON [dbo].[Users]
(
	[PhoneHash] ASC,
	[DeviceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  StoredProcedure [dbo].[ContactCreate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ContactCreate]
	@UsersSN BIGINT
,	@PhoneHash		VARCHAR(50)
,	@PhoneName		VARCHAR(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO Contacts
	(
		UsersSN
	,	PhoneHash
	,	PhoneName
	)
	SELECT
		@UsersSN
	,	@PhoneHash
	,	@PhoneName
	
END


GO
/****** Object:  StoredProcedure [dbo].[ContactGetInfo]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ContactGetInfo]
	@UsersSN				BIGINT = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT
		PhoneHash
	,	PhoneName
	FROM
		Contacts
	WHERE
		UsersSN = @UsersSN
END


GO
/****** Object:  StoredProcedure [dbo].[ContactGetList]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ContactGetList] 

	@UsersSN BIGINT
,	@TotalCount	INT OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	-- Insert statements for procedure here
	SELECT @TotalCount = COUNT(*) FROM Contacts WHERE UsersSN = @UsersSN
	
	SELECT
		UsersSN
	,	PhoneHash
	,	PhoneName
	FROM
		Contacts
	WHERE
		UsersSN = @UsersSN
		
END


GO
/****** Object:  StoredProcedure [dbo].[ContactUpdate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ContactUpdate]
	@UsersSN BIGINT = NULL
,	@PhoneHash VARCHAR(50) = NULL
,	@PhoneName VARCHAR(50) = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE Contacts
	SET	
		PhoneHash	= ISNULL(PhoneHash, @PhoneHash)
	,	PhoneName	= ISNULL(PhoneName, @PhoneName)
	WHERE
		UsersSN = @UsersSN
	
END


GO
/****** Object:  StoredProcedure [dbo].[EventCreate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[EventCreate]

	@Catogory TINYINT
,	@CatogoryName NVARCHAR(20)
,	@PhoneHash TEXT

,	@EventSN BIGINT OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
    SELECT TOP 1 @EventSN = EventSN FROM Events ORDER BY EventSN DESC
    
    IF @EventSN IS NULL
		BEGIN
			SET @EventSN = 1
		END
    ELSE
		BEGIN
			SET @EventSN = @EventSN + 1
		END

	INSERT INTO Events
	(
		EventSN
	,	CreateDate
	,	UpdateDate
	,	Catogory
	,	CatogoryName
	,	CallCount
	,	PhoneHash
	,	IsDelete
	)
	VALUES
	(
		@EventSN
	,	GETDATE()
	,	GETDATE()
	,	@Catogory
	,	@CatogoryName
	,	0
	,	@PhoneHash
	,	0
	)
	
END


GO
/****** Object:  StoredProcedure [dbo].[EventGetInfo]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[EventGetInfo]

	@EventSN BIGINT

,	@CreateDate DATETIME OUTPUT
,	@UpdateDate DATETIME OUTPUT
,	@Catogory TINYINT OUTPUT
,	@CatogoryName NVARCHAR(20) OUTPUT
,	@CallCount INT OUTPUT
,	@PhoneHash TEXT OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT
		@CreateDate = CreateDate
	,	@UpdateDate = UpdateDate
	,	@Catogory = Catogory
	,	@CatogoryName = CatogoryName
	,	@CallCount = CallCount
	,	@PhoneHash = PhoneHash
	FROM
		Events
	WHERE
		EventSN = @EventSN AND IsDelete = 0
END


GO
/****** Object:  StoredProcedure [dbo].[EventGetList]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[EventGetList] 

	@TotalCount	INT OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	-- Insert statements for procedure here
	SELECT @TotalCount = COUNT(*) FROM Events
	
	SELECT
		EventSN
	,	CreateDate
	,	UpdateDate
	,	Catogory
	,	CatogoryName
	,	PhoneHash
	FROM
		Events
		
END


GO
/****** Object:  StoredProcedure [dbo].[EventUpdate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[EventUpdate]
	@EventSN BIGINT = NULL

,	@Catogory TINYINT = NULL
,	@CatogoryName NVARCHAR(20) = NULL
,	@CallCount INT = NULL
,	@PhoneHash TEXT = NULL
,	@IsDelete TINYINT = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE Events
	SET	
		Catogory = ISNULL(Catogory, @Catogory)
	,	CatogoryName = ISNULL(CatogoryName, @CatogoryName)
	,	CallCount = ISNULL(CallCount, @CallCount)
	,	PhoneHash = ISNULL(PhoneHash, @PhoneHash)
	,	IsDelete = ISNULL(IsDelete, @IsDelete)
	WHERE
		EventSN = @EventSN
	
END


GO
/****** Object:  StoredProcedure [dbo].[InstallUserCreate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[InstallUserCreate]

	@UserSN BIGINT = NULL
,	@UserSN_Object BIGINT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
	INSERT INTO InstallUsers
	(
		UserSN
	,	UserSN_Object
	)
	SELECT
		@UserSN
	,	@UserSN_Object
	
END


GO
/****** Object:  StoredProcedure [dbo].[InstallUserGetInfo]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[InstallUserGetInfo]

	@UserSN BIGINT = NULL
,	@UserSN_Object BIGINT

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT
		@UserSN = UserSN
	,	@UserSN_Object = UserSN_Object
	FROM
		InstallUsers
	WHERE
		UserSN = @UserSN AND UserSN_Object = @UserSN_Object
END


GO
/****** Object:  StoredProcedure [dbo].[InstallUserGetList]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[InstallUserGetList] 

	@UserSN BIGINT = NULL
,	@TotalCount	INT OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	-- Insert statements for procedure here
	SELECT @TotalCount = COUNT(*) FROM InstallUsers WHERE UserSN = @UserSN
	
	SELECT
		UserSN
	,	UserSN_Object
	FROM
		InstallUsers
	WHERE
		UserSN = @UserSN
		
END


GO
/****** Object:  StoredProcedure [dbo].[InstallUserUpdate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[InstallUserUpdate]
	@UserSN			BIGINT = NULL
,	@UserSN_Object	BIGINT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE InstallUsers
	SET	
		UserSN_Object = ISNULL(UserSN_Object, @UserSN_Object)
	WHERE
		UserSN = @UserSN
	
END


GO
/****** Object:  StoredProcedure [dbo].[UserCreate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserCreate]

	@PhoneHash		VARCHAR(50) = NULL
,	@PushKey		VARCHAR(50) = NULL
,	@DeviceID		VARCHAR(50) = NULL
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    
    DECLARE @UserSN BIGINT
    
    SELECT @UserSN = UserSN FROM Users ORDER BY UserSN DESC
    
    IF @UserSN IS NULL
		BEGIN
			SET @UserSN = 1
		END
    ELSE
		BEGIN
			SET @UserSN = @UserSN + 1
		END
    
	INSERT INTO Users
	(
		UserSN
	,	PhoneHash
	,	CreateDate
	,	UpdateDate
	,	PushKey
	,	DeviceID
	)
	SELECT
		@UserSN
	,	@PhoneHash
	,	GETDATE()
	,	GETDATE()
	,	@PushKey
	,	@DeviceID
	
END


GO
/****** Object:  StoredProcedure [dbo].[UserEventCreate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserEventCreate]

	@UserSN BIGINT = NULL
,	@Catogory TINYINT = NULL
,	@CatogoryName NVARCHAR(20) = NULL
,	@PhoneHash TEXT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    DECLARE @EventSN BIGINT
    
	EXEC EventCreate @Catogory = @Catogory, @CatogoryName = @CatogoryName, @PhoneHash = @PhoneHash, @EventSN = @EventSN OUTPUT
	
	INSERT INTO UserEvents
	(
		UserSN
	,	EventSN
	)
	SELECT
		@UserSN
	,	@EventSN
	
END


GO
/****** Object:  StoredProcedure [dbo].[UserEventGetInfo]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserEventGetInfo]

	@UserSN BIGINT = NULL
,	@EventSN BIGINT = NULL

,	@CreateDate DATETIME = NULL OUTPUT
,	@UpdateDate DATETIME = NULL OUTPUT
,	@Catogory TINYINT = NULL OUTPUT
,	@CatogoryName NVARCHAR(20) = NULL OUTPUT
,	@CallCount INT = NULL OUTPUT
,	@PhoneHash TEXT = NULL OUTPUT

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT
		@CreateDate = B.CreateDate
	,	@UpdateDate = B.UpdateDate
	,	@Catogory = B.Catogory
	,	@CatogoryName = B.CatogoryName
	,	@CallCount = B.CallCount
	,	@PhoneHash = B.PhoneHash
	FROM
		UserEvents A INNER JOIN Events B ON A.EventSN = B.EventSN
	WHERE
		A.UserSN = @UserSN AND B.EventSN = @EventSN AND B.IsDelete = 0
END


GO
/****** Object:  StoredProcedure [dbo].[UserEventGetList]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserEventGetList] 

	@UserSN BIGINT = NULL
,	@TotalCount	INT OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	-- Insert statements for procedure here
	SELECT @TotalCount = COUNT(*) FROM UserEvents WHERE UserSN = @UserSN
	
	SELECT
		A.UserSN
	,	B.EventSN
	,	B.CreateDate
	,	B.UpdateDate
	,	B.Catogory
	,	B.CatogoryName
	,	B.CallCount
	,	B.PhoneHash
	FROM
		UserEvents A INNER JOIN Events B ON A.EventSN = B.EventSN
	WHERE
		UserSN = @UserSN AND B.IsDelete = 0
		
END


GO
/****** Object:  StoredProcedure [dbo].[UserEventUpdate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserEventUpdate]
	@UserSN BIGINT = NULL
,	@EventSN BIGINT = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE UserEvents
	SET	
		EventSN = ISNULL(EventSN, @EventSN)
	WHERE
		UserSN = @UserSN
	
END


GO
/****** Object:  StoredProcedure [dbo].[UserGetInfo]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserGetInfo]
	@UserSN				BIGINT = NULL

,	@PhoneHash		VARCHAR(50) OUTPUT
,	@CreateDate		DATETIME OUTPUT
,	@UpdateDate		DATETIME OUTPUT
,	@PushKey		VARCHAR(50) OUTPUT
,	@DeviceID		VARCHAR(50) OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT
		@PhoneHash	= PhoneHash
	,	@CreateDate	= CreateDate
	,	@UpdateDate	= UpdateDate
	,	@PushKey	= PushKey
	,	@DeviceID	= DeviceID
	FROM
		Users
	WHERE
		UserSN = @UserSN
END


GO
/****** Object:  StoredProcedure [dbo].[UserGetList]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserGetList] 

	@TotalCount	INT OUTPUT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	-- Insert statements for procedure here
	SELECT @TotalCount = COUNT(*) FROM Users
	
	SELECT
		UserSN
	,	PhoneHash
	,	CreateDate
	,	UpdateDate
	,	PushKey
	,	DeviceID
	FROM
		Users
		
END


GO
/****** Object:  StoredProcedure [dbo].[UserUpdate]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[UserUpdate]
	@UserSN				BIGINT = NULL
,	@PhoneHash		VARCHAR(50) = NULL
,	@PushKey		VARCHAR(50) = NULL
,	@DeviceID		VARCHAR(50) = NULL

AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE Users
	SET	
		PhoneHash = ISNULL(@PhoneHash, PhoneHash)
	,	UpdateDate = GETDATE()
	,	PushKey = ISNULL(@PushKey, PushKey)
	,	DeviceID = ISNULL(@DeviceID, DeviceID)
	WHERE
		UserSN = @UserSN
	
END


GO
/****** Object:  DdlTrigger [rds_deny_backups_trigger]    Script Date: 2015. 3. 17. 오전 1:48:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [rds_deny_backups_trigger] ON DATABASE WITH EXECUTE AS 'dbo' FOR
 ADD_ROLE_MEMBER, GRANT_DATABASE AS BEGIN
   SET ANSI_PADDING ON;
 
   DECLARE @data xml;
   DECLARE @user sysname;
   DECLARE @role sysname;
   DECLARE @type sysname;
   DECLARE @sql NVARCHAR(MAX);
   DECLARE @permissions TABLE(name sysname PRIMARY KEY);
   
   SELECT @data = EVENTDATA();
   SELECT @type = @data.value('(/EVENT_INSTANCE/EventType)[1]', 'sysname');
    
   IF @type = 'ADD_ROLE_MEMBER' BEGIN
      SELECT @user = @data.value('(/EVENT_INSTANCE/ObjectName)[1]', 'sysname'),
       @role = @data.value('(/EVENT_INSTANCE/RoleName)[1]', 'sysname');

      IF @role IN ('db_owner', 'db_backupoperator') BEGIN
         SELECT @sql = 'DENY BACKUP DATABASE, BACKUP LOG TO ' + QUOTENAME(@user);
         EXEC(@sql);
      END
   END ELSE IF @type = 'GRANT_DATABASE' BEGIN
      INSERT INTO @permissions(name)
      SELECT Permission.value('(text())[1]', 'sysname') FROM
       @data.nodes('/EVENT_INSTANCE/Permissions/Permission')
      AS DatabasePermissions(Permission);
      
      IF EXISTS (SELECT * FROM @permissions WHERE name IN ('BACKUP DATABASE',
       'BACKUP LOG'))
         RAISERROR('Cannot grant backup database or backup log', 15, 1) WITH LOG;       
   END
END


GO
ENABLE TRIGGER [rds_deny_backups_trigger] ON DATABASE
GO
USE [master]
GO
ALTER DATABASE [IMFREE] SET  READ_WRITE 
GO
