USE [WildPeacock]
GO
/****** Object:  Table [dbo].[Tag]    Script Date: 08/11/2013 11:40:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Tag](
	[id] [int] NOT NULL identity,
	[tagId] [int] ,
	[name] [varchar](64) NOT NULL,
	[created] [datetime] NOT NULL,
 CONSTRAINT [PK_Tag] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Publishing]    Script Date: 08/11/2013 11:40:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Publishing](
	[id] [int] NOT NULL identity,
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Category]    Script Date: 08/11/2013 11:40:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] NOT NULL identity,
	[parentId] [int] ,
	[name] [varchar](128) NOT NULL,
	[description] [varchar](128) NOT NULL,
	[created] [datetime] NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Authority]    Script Date: 08/11/2013 11:40:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Authority](
	[id] [int] NOT NULL identity,
	[username] [varchar](64) NOT NULL,
	[password] [varchar](32) NOT NULL,
	[created] [datetime] NOT NULL,
 CONSTRAINT [PK_Authority] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Product]    Script Date: 08/11/2013 11:40:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] NOT NULL identity,
	[created] [datetime] NOT NULL,
	[title] [varchar](128) NOT NULL,
	[subTitle] [varchar](64) NOT NULL,
	[description] [varchar](4096) NOT NULL,
	[photoURL] [varchar](256) NOT NULL,
	[price] [money] NOT NULL,
	[categoryID] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] 
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ProductTags]    Script Date: 08/11/2013 11:40:39 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[CustomPages]    Script Date: 10/31/2013 14:48:40 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[CustomPages]') AND type in (N'U'))
DROP TABLE [dbo].[CustomPages]
GO

USE [WildPeacock]
GO

/****** Object:  Table [dbo].[CustomPages]    Script Date: 10/31/2013 14:48:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[CustomPages](
	[id] [int] NOT NULL,
	[pageName] [varchar](50) NOT NULL,
	[title] [varbinary](50) NOT NULL,
	[description] [varchar](8000) NOT NULL,
	[siteSpaceType] [varchar](8000) NOT NULL,
	[inserted] [datetime] NOT NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


/****** Object:  ForeignKey [FK_Category_Category]    Script Date: 08/11/2013 11:40:39 ******/
ALTER TABLE [dbo].[Category]  WITH CHECK ADD  CONSTRAINT [FK_Category_Category] FOREIGN KEY([parentId])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Category] CHECK CONSTRAINT [FK_Category_Category]
GO
/****** Object:  ForeignKey [FK_Product_Category]    Script Date: 08/11/2013 11:40:39 ******/
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
/****** Object:  ForeignKey [FK_ProductTags_Product]    Script Date: 08/11/2013 11:40:39 ******/
ALTER TABLE [dbo].[ProductTags]  WITH CHECK ADD  CONSTRAINT [FK_ProductTags_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[ProductTags] CHECK CONSTRAINT [FK_ProductTags_Product]
GO
/****** Object:  ForeignKey [FK_ProductTags_Tag]    Script Date: 08/11/2013 11:40:39 ******/
ALTER TABLE [dbo].[ProductTags]  WITH CHECK ADD  CONSTRAINT [FK_ProductTags_Tag] FOREIGN KEY([tagId])
REFERENCES [dbo].[Tag] ([id])
GO
ALTER TABLE [dbo].[ProductTags] CHECK CONSTRAINT [FK_ProductTags_Tag]
GO
/****** Object:  ForeignKey [FK_Tag_Tag]    Script Date: 08/11/2013 11:40:39 ******/
ALTER TABLE [dbo].[Tag]  WITH CHECK ADD  CONSTRAINT [FK_Tag_Tag] FOREIGN KEY([tagId])
REFERENCES [dbo].[Tag] ([id])
GO
ALTER TABLE [dbo].[Tag] CHECK CONSTRAINT [FK_Tag_Tag]
GO
