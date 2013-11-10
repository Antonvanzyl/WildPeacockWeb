USE [WildPeacock]
GO
ALTER TABLE [dbo].[Tag] DROP CONSTRAINT [FK_Tag_Tag]
GO
ALTER TABLE [dbo].[ProductTags] DROP CONSTRAINT [FK_ProductTags_Tag]
GO
ALTER TABLE [dbo].[ProductTags] DROP CONSTRAINT [FK_ProductTags_Product]
GO
ALTER TABLE [dbo].[Product] DROP CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[Category] DROP CONSTRAINT [FK_Category_Category]
GO
/****** Object:  Table [dbo].[Tag]    Script Date: 10/11/2013 05:57:02 PM ******/
DROP TABLE [dbo].[Tag]
GO
/****** Object:  Table [dbo].[Publishing]    Script Date: 10/11/2013 05:57:02 PM ******/
DROP TABLE [dbo].[Publishing]
GO
/****** Object:  Table [dbo].[ProductTags]    Script Date: 10/11/2013 05:57:02 PM ******/
DROP TABLE [dbo].[ProductTags]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 10/11/2013 05:57:02 PM ******/
DROP TABLE [dbo].[Product]
GO
/****** Object:  Table [dbo].[CustomPages]    Script Date: 10/11/2013 05:57:02 PM ******/
DROP TABLE [dbo].[CustomPages]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/11/2013 05:57:02 PM ******/
DROP TABLE [dbo].[Category]
GO
/****** Object:  Table [dbo].[Authority]    Script Date: 10/11/2013 05:57:02 PM ******/
DROP TABLE [dbo].[Authority]
GO
/****** Object:  Table [dbo].[Authority]    Script Date: 10/11/2013 05:57:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Authority](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](64) NOT NULL,
	[password] [varchar](32) NOT NULL,
	[created] [datetime] NOT NULL,
 CONSTRAINT [PK_Authority] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/11/2013 05:57:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[parentId] [int] NULL,
	[name] [varchar](128) NOT NULL,
	[description] [varchar](128) NOT NULL,
	[created] [datetime] NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CustomPages]    Script Date: 10/11/2013 05:57:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CustomPages](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[pageName] [varchar](64) NOT NULL,
	[title] [varchar](64) NOT NULL,
	[description] [varchar](8000) NOT NULL,
	[siteSpaceType] [varchar](32) NOT NULL,
	[inserted] [datetime] NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Product]    Script Date: 10/11/2013 05:57:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[created] [datetime] NOT NULL,
	[title] [varchar](128) NOT NULL,
	[subTitle] [varchar](64) NOT NULL,
	[description] [varchar](4096) NOT NULL,
	[photo] [image] NULL,
	[price] [money] NOT NULL,
	[categoryID] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ProductTags]    Script Date: 10/11/2013 05:57:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductTags](
	[productId] [int] NOT NULL,
	[tagId] [int] NOT NULL,
	[created] [datetime] NOT NULL,
 CONSTRAINT [PK_ProductTags] PRIMARY KEY CLUSTERED 
(
	[productId] ASC,
	[tagId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Publishing]    Script Date: 10/11/2013 05:57:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Publishing](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[created] [datetime] NOT NULL,
	[title] [varchar](128) NOT NULL,
	[subtitle] [varchar](64) NOT NULL,
	[description] [varchar](4096) NOT NULL,
	[eventDate] [datetime] NOT NULL,
	[section] [varchar](50) NOT NULL,
	[read_count] [int] NOT NULL,
 CONSTRAINT [PK_Publishing] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Tag]    Script Date: 10/11/2013 05:57:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Tag](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[tagId] [int] NULL,
	[name] [varchar](64) NOT NULL,
	[created] [datetime] NOT NULL,
 CONSTRAINT [PK_Tag] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Category]  WITH CHECK ADD  CONSTRAINT [FK_Category_Category] FOREIGN KEY([parentId])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Category] CHECK CONSTRAINT [FK_Category_Category]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[ProductTags]  WITH CHECK ADD  CONSTRAINT [FK_ProductTags_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[ProductTags] CHECK CONSTRAINT [FK_ProductTags_Product]
GO
ALTER TABLE [dbo].[ProductTags]  WITH CHECK ADD  CONSTRAINT [FK_ProductTags_Tag] FOREIGN KEY([tagId])
REFERENCES [dbo].[Tag] ([id])
GO
ALTER TABLE [dbo].[ProductTags] CHECK CONSTRAINT [FK_ProductTags_Tag]
GO
ALTER TABLE [dbo].[Tag]  WITH CHECK ADD  CONSTRAINT [FK_Tag_Tag] FOREIGN KEY([tagId])
REFERENCES [dbo].[Tag] ([id])
GO
ALTER TABLE [dbo].[Tag] CHECK CONSTRAINT [FK_Tag_Tag]
GO
