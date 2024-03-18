USE [master]
GO
CREATE DATABASE Groove_Galaxy_Records
GO
USE Groove_Galaxy_Records
GO
CREATE TABLE Category(
	id INT PRIMARY KEY IDENTITY(1,1),
	name NVARCHAR(100)
)

CREATE TABLE Country(
	id INT PRIMARY KEY IDENTITY(1,1),
	name NVARCHAR(100)
)

-- status: 1: in stock, 0: out of stock
CREATE TABLE Product(
	id INT PRIMARY KEY IDENTITY(1,1),
	name NVARCHAR(150),
	price FLOAT,
	quantity INT,
	description NVARCHAR(4000),
	image NVARCHAR(500),
	status BIT,
	categoryId INT,
	countryId INT,
	FOREIGN KEY (categoryId) REFERENCES Category(id),
	FOREIGN KEY (countryId) REFERENCES Country(id),
	qtysold int
)

-- roleid: 0: admin, 1: user
CREATE TABLE Users(
	username VARCHAR(50) PRIMARY KEY,
	fullname NVARCHAR(100),
	phoneNumber NVARCHAR(11),
	email VARCHAR(100),
	gender INT,
	location NVARCHAR(100),
	password VARCHAR(100),
	dob DATE,
	roleId INT,
	image NVARCHAR(500),
	address NVARCHAR(200)
)

-- status: 0: not paid, 1: paid
CREATE TABLE [Order](
	id INT PRIMARY KEY IDENTITY(1,1),
	[date] DATE,
	username VARCHAR(50),
	totalMoney FLOAT,
	status BIT,
	FOREIGN KEY (username) REFERENCES Users(username)
)

CREATE TABLE OrderDetail (
	ProductID INT,
	OrderID INT,
	quantity INT,
	price FLOAT,
    FOREIGN KEY (ProductID) REFERENCES Product(id),
    FOREIGN KEY (OrderID) REFERENCES [Order](id),
    PRIMARY KEY (OrderID, ProductID)
);

INSERT INTO Category VALUES
('CD'),
('VINYL'),
('CASSETTE')

INSERT INTO Country VALUES
('US-UK'), ('Korea'),('Vietnam')

INSERT INTO Product VALUES
('TAYLOR SWIFT - MIDNIGHTS (THE LATE NIGHT EDITION)', 16.35, 130,
'Each CD album includes:
21 Songs
1 of 4 collectible albums with unique front and back cover art
1 of 4 unique, collectible disc artwork
1 of 4 unique marbled color CD discs (the Moonstone Blue Edition features a moonstone blue marbled color disc)
A collectible lyric booklet with never-before-seen photos',
'./img/us_uk/midnighttlne_cd.webp',1, 1, 1, 0),

('TAYLOR SWIFT - RED (TAYLOR''S VERSION)', 44.25, 190, 
'Four vinyl LP set. 2021 release, the singer/songwriter''s re-recording of her 2012 album. 
"Musically and lyrically, Red resembled a heartbroken person. 
It was all over the place, a fractured mosaic of feelings that somehow all fit together in the end. 
Happy, free, confused, lonely, devastated, euphoric, wild, and tortured by memories past. 
Like trying on pieces of a new life, I went into the studio and experimented with different sounds and collaborators. 
And I''m not sure if it was pouring my thoughts into this album, hearing thousands of your voices sing the lyrics back to me in passionate solidarity, 
or if it was simply time, but something was healed along the way...
This will be the first time you hear all 30 songs that were meant to go on Red. 
And hey, one of them is even ten minutes long." - Taylor Swift. 
Includes 9 unreleased songs from the vault. Play at 45rpm for optimal sound quality.',
'https://i5.walmartimages.com/seo/Taylor-Swift-Red-Taylor-s-Version-4-LP-Explicit-Vinyl_9ad29a60-d401-4e98-9516-8cd5c8c0c9c9.b6a905363566bf39de5700836f76f15f.png',
1, 2, 1, 0),
 
('Taylor Swift - The Tortured Poets Department', 12.99, 1499,
'16 Tracks + Bonus Track “The Manuscript”
Collectible CD album in single jewel case with unique front and back cover art
1 Disc album with collectible disc artwork
A collectible 20-page booklet that includes all song lyrics and never-before-seen photos
10”x10” Double-Sided Poster
Side 1 includes full size photograph of Taylor Swift
Side 2 includes one handwritten lyric, unique to this CD',
'https://product.hstatic.net/1000304920/product/taylor-swift-the-tortured-poets-department-dia-cd_797195893b37498cbe5ff4786954b92a_grande.png',
1, 1, 1, 0),

('Taylor Swift - 1989 (Taylor''s Version)', 15.99, 304,
'Each Cassette Album includes:
21 Songs
Including 5 previously unreleased songs from The Vault
Collectible never-before-seen photos
1 double-sided Cassette shell. Side A is Aquamarine Green, Side B is Sunrise Boulevard Yellow',
'./img/us_uk/1989tv_cassette.webp',1, 3, 1, 0),

('Taylor Swift - Midnights', 13.99, 560,
'Each CD album includes:
13 Songs
1 of 4 collectible albums with unique front and back cover art
1 of 4 unique, collectible disc artwork
1 of 4 unique marbled color CD discs (the Moonstone Blue Edition features a moonstone blue marbled color disc)
A collectible lyric booklet with never-before-seen photos',
'./img/us_uk/midnights_cd.webp',1, 1, 1, 0),

('TAYLOR SWIFT - FEARLESS (TAYLOR''S VERSION) (LIMITED EDITION)', 18.99, 1000,
'Cassette 1
1. Fearless (Taylor''s Version)
2. Fifteen (Taylor''s Version)
3. Love Story (Taylor''s Version)
4. Hey Stephen (Taylor''s Version)
5. White Horse (Taylor''s Version)
6. You Belong with Me (Taylor''s Version)
7. Breathe (Taylor''s Version)
8. Tell Me Why (Taylor''s Version)
9. You''re Not Sorry (Taylor''s Version)
10. The Way I Loved You (Taylor''s Version)
11. Forever & Always (Taylor''s Version)
12. The Best Day (Taylor''s Version)
13. Change (Taylor''s Version)

Cassette 2
1. Jump Then Fall (Taylor''s Version)
2. Untouchable (Taylor''s Version)
3. Forever & Always (Piano Version) (Taylor''s Version)
4. Come In with the Rain (Taylor''s Version)
5. Superstar (Taylor''s Version)
6. The Other Side of the Door (Taylor''s Version)
7. Today Was A Fairytale (Taylor''s Version)
8. You All Over Me (From The Vault)
9. Mr. Perfectly Fine (From The Vault)
10. We Were Happy (From The Vault)
11. That''s When (From The Vault)
12. Don''t You (From The Vault)
13. Bye Bye Baby (From The Vault)
14. Love Story (Taylor''s Version) - Elvira Remix',
'https://product.hstatic.net/1000304920/product/taylor-swift-fearless-taylor-s-version-bang-cassette_tape-cat-set_ede538fb98cd4400aeaee5160ab5d34c.png',
1, 3, 1, 0),

('Taylor Swift - evermore (Target Limited Edition)', 24.99, 1989, 
'Taylor Swift has announced her ninth studio album, evermore; folklore''s sister record. 
These songs were created with Aaron Dessner, Jack Antonoff, WB and Justin Vernon.

1. willow
2. champagne problems
3. gold rush
4. ‘tis the damn season
5. tolerate it
6. no body, no crime (feat. HAIM)
7. happiness
8. dorothea
9. coney island (feat. The National)
10. ivy
11. cowboy like me
12. long story short
13. marjorie
14. closure
15. evermore (feat. Bon Iver)
16. right where you left me
17. it’s time to go',
'./img/us_uk/evermore_vinyl.png',1, 2, 1, 0),

('Taylor Swift - folklore (Target Limited Edition)', 23.99, 189, 
'Brand new Taylor Swift Folklore album on vinyl, deluxe green-colored Stolen Lullabies variant with limited edition cover art.

Track List
1. "The 1"
2. "Cardigan"
3. "The Last Great American Dynasty"
4. "Exile" (featuring Bon Iver)
5. "My Tears Ricochet"
6. "Mirrorball"
7. "Seven"
8. "August"
9. "This Is Me Trying"
10. "Illicit Affairs"
11. "Invisible String"
12. "Mad Woman"
13. "Epiphany"
14. "Betty"
15. "Peace"
16. "Hoax"
17. "The Lakes"',
'https://i.ebayimg.com/images/g/8tQAAOSwqfRgtm7s/s-l1200.webp',
1, 2, 1, 0), 

('Taylor Swift - Lover (Live In Paris) - Exclusive Limited Edition', 39.99, 980, 
'Lover Live In Paris - Exclusive Limited Edition Heart Shaped Pink & Blue Marble Colored Vinyl 2LP

Tracklist
A1 Me! (Live From Paris)
A2 The Archer (Live From Paris)
B3 Death By A Thousand Cuts (Live From Paris)
B4 Cornelia Street (Live From Paris)
C5 The Man (Live From Paris)
C6 Daylight (Live From Paris)
D7 You Need To Calm Down (Live From Paris)
D8 Lover (Live From Paris)',
'./img/us_uk/loverlivefromparis.png',1, 2, 1, 0), 

('TAYLOR SWIFT - Lover', 20.99, 369, 
'TAYLOR SWIFT Lover Cassette
Tracklist:

1. I Forgot That You Existed
2. Cruel Summer
3. Lover
4. The Man
5. The Archer
6. I Think He Knows
7. Miss Americana & the Heartbreak Prince
8. Paper Rings
9. Cornelia Street
10. Death By a Thousand Cuts
11. London Boy
12. Soon You’ll Get Better
13. False God
14. You Need to Calm Down
15. Afterglow
16. ME! (feat. Brendon Urie)
17. It’s Nice to Have a Friend
18. Daylight',
'./img/us_uk/lover.png',1, 3, 1, 0), 

('BLACKPINK – THE ALBUM (Limited Edition)', 33.49, 234, 
'BLACKPINK’s debut album on pink vinyl set to release on January 29, 2021. 
The album includes features from Selena Gomez & Cardi B!

1. How You Like That
2. Ice Cream
3. Pretty Savage
4. Bet You Wanna
5. Lovesick Girls
6. Crazy Over You
7. Love To Hate Me
8. You Never Know',
'https://districtm.vn/wp-content/uploads/2021/06/Black-pink-vinyl-lp-800x800.jpg.webp',
1, 2, 2, 0), 

('BTS - "Yet To Come" Single CD', 1.99, 30, 
'As a gift to their fans, BTS is releasing a special CD single for their focus track “Yet To Come.” 
This new track “Yet To Come” was written as a tribute and thank-you for the past nine years that BTS and ARMY have journeyed together and with the hopeful message that the best for both BTS and their ARMY is truly yet to come. 
Contents include a CD enveloped in a CD jacket.',
'https://shop.bts-official.us/cdn/shop/products/SingleCDpng.png?v=1654563294',
1, 1, 2, 0), 

('BTS - FAKE LOVE / AIRPLANE PT.2', 13.99, 193, 
'BTS releases an exclusive single for the Japanese market. 
Includes "Fake Love," Japanese version of "Airplane pt.2," and Japanese original song "Bird" written by Yasushi Akimoto. Universal. 2018.',
'https://product.hstatic.net/1000304920/product/bts-fake-love-airplane-pt.2-cd-dvd-music-video-02_5754c3360e4b44dd933df1b4d9e33bb4_master.jpeg',
1, 1, 2, 0), 

('BLACKPINK - BORN PINK', 33.49, 567, 
'Package Box, CD, Photobook (80P), Envelope, Random Large Photocard (1 of 4), Random Postcard (1 of 4), Random Instant Films (2 of 8), Random Selfie Photocard (1 of 8), Sticker (1 EA/First Press Only), Poster (1 EA/First Press Only).
Version A is Pink.',
'https://koreajoongangdaily.joins.com/data/photo/2022/08/11/263d63a0-7acd-4d60-add6-ca316cf99e1b.jpg',
1, 2, 2, 0), 

(N'Rosé (BLACKPINK) - R (CD Single)', 14.99, 480, 
N'After conquering the K-Pop world as part of superstar girl group BLACKPINK, vocalist ROSÉ makes her debut as a solo artist with her project "R". 
The project includes two singles "On the Ground" & "Gone". 
This is a new, unopened CD in its original packaging.',
'https://merchbar.imgix.net/product/cdified/upc/81/602435978185.jpg?q=40&auto=compress,format&w=992',
1, 1, 2, 0), 

(N'HOÀNG THUỲ LINH - LINK (BURNT ORANGE)', 15.49, 204, 
N'Hoàng Thuỳ Linh - LINK (Burnt Orange) - Băng Cassette Nhạc Việt Nam',
'https://product.hstatic.net/1000304920/product/hoang-thuy-linh-link-burnt-orange-bang-cassette_ef5f69ed9a124a6189e2dbe5f8c85f32.jpg',
1, 3, 3, 0), 

(N'PHÙNG KHÁNH LINH - CITOPIA - THE 2ND STUDIO ALBUM (VINYL LP)', 34.89, 29, 
N'Album Details: 

1 LP
12 Pages Booklet: W305 x H305
1 Obi
Single Jacket
Full 4 photocards & 1 Handwritten Thank-you Notes from Phùng Khánh Linh',
'https://product.hstatic.net/1000304920/product/vinyl_v1_4dbf444252234af28f6c7741eea6a05d.png',
1, 2, 3, 0), 

(N'CÁ HỒI HOANG - GẤP', 10.99, 789, 
N'"Gấp" special cassette edition. Includes:

- Gấp cassette (hearable)
- Download code for "Gấp" digital album. 

Tracklisting:
1 - Hết Mực
2 - Beertalks
3 - Đừng Buồn
4 - Tầng Thượng 102
5 - Có Thể
6 - Lãng Đãng
7 - Đi Tìm
8 - Mơ
9 - Treo
10 - Bandamusical',
'https://product.hstatic.net/1000304920/product/ca-hoi-hoang-gap-bang-cassette_e954d83429a74b268f160e6d61ed34d0_master.jpeg',
1, 3, 3, 0), 

(N'LUNA ĐÀO - THÔNG MINH CHO ĐẾN KHI YÊU (EP)', 9.99, 471, 
N'Tracklist:
Tiêu Chuẩn Kép
Thông Minh Cho Đến Khi Yêu
Là Ai Mà Đánh Giá
Outro
 Album Details: 

3-Panel Digipak w/ Black Tray
1 Booklet - 20 pages: W145x H125
1 CD-R
2 Photocards: W55 x H85',
'https://product.hstatic.net/1000304920/product/20240116_luna_dao-thong_minh_cho_den_khi_yeu-02_08d6a68f10ae412397ee779fb82cf39c_master.jpg',
1, 1, 3, 0), 

(N'HÀ ANH TUẤN - CUỐI NGÀY NGƯỜI ĐÀN ÔNG MỘT MÌNH (VINYL LP)', 29.99, 618, 
N'Side A:

1. Cà Phê Một Mình
2. Giấc Mơ Mùa Thu
3. Chuyện Của Mùa Đông
4. Đêm Nằm Mơ Phố  

Side B: 
5. Hà Nội Ngày Ấy
6. Mưa Rừng
7. Tùy Hứng Lý Qua Cầu
8. Thành Phố Buồn',
'https://product.hstatic.net/1000304920/product/dia-than-ha-anh-tuan-cuoi-ngay-nguoi-dan-ong-mot-minh-vinyl-lp_9aa1553740d6426c9f1b96b2be5f30cf_master.jpeg',
1, 2, 3, 0), 

('Lana Del Rey - “Did you know that there’s a tunnel under Ocean Blvd” (Target Limited Edition)', 34.99, 1985, 
'Side A:
1. The Grants
2. Did you know that there''s a tunnel under Ocean Blvd
3. Sweet
4. A&W

Side B:
5. Judah Smith Interlude
6. Candy Necklace (feat. Jon Batiste)
7. Jon Batiste Interlude
8. Kintsugi

Side C:
9. Fingertips
10. Paris, Texas (feat. SYML)
11. Grandfather please stand on the shoulders of my father while he''s deep-sea fishing (feat. RIOPY)
12. Let The Light In (feat. Father John Misty)

Side D:
13. Margaret (feat. Bleachers)
14. Fishtail
15. Peppers (feat. Tommy Genesis)
16. Taco Truck x VB',
'https://target.scene7.com/is/image/Target/GUEST_dae8b68b-b897-47bd-9ffb-7b16cd56cd6b?wid=600&hei=600&qlt=80&fmt=webp',
1, 2, 1, 0), 

('LANA DEL REY - Blue Banisters', 16.99, 1000, 
'Tracklist:
1        Text Book
2        Blue Banisters
3        Arcadia
4        Interlude - The Trio
5        Black Bathing Suit
6        If You Lie Down With Me
7        Beautiful
8        Violets Of Roses
9        Dealer
10        Thunder
11        Wildflower Wildfire
12        Nectar Of The Gods
13        Living Legend
14        Cherry Blossom
15        Sweet Carolina',
'https://bizweb.dktcdn.net/100/411/628/products/lana-del-rey-blue-banisters-signed-art-card-transparent.png?v=1640627529813',
1, 1, 1, 0), 

('Taylor Swift - Speak Now (Taylor''s Version) (Orchid Marble Vinyl 3LP) (Limited Edition)', 35.99, 79, 
'Each Vinyl Album Includes:

22 Songs

Including 6 previously unreleased Songs From The Vault

Collectible album jacket with unique front and back cover art

3 unique Orchid marbled color vinyl discs

Collectible album sleeves including lyrics and never-before-seen photos

Full size gatefold photograph and prologue',
'https://product.hstatic.net/1000304920/product/ylor-swift-speak-now-taylor-s-version-orchid-marble-vinyl-3lp-dia-than_6ccc65e09fcb477b9d86b324f6b85015_master.png',
1, 2, 1, 0), 

('Taylor Swift - The Tortured Poets Department', 19.99, 1312, 
'16 Tracks + Bonus Track “The Manuscript”
Replica of Taylor Swift''s handwritten lyrics unique to this cassette and collectible never-before-seen photos
1 Double-Sided Cassette Shell in Ghosted White',
'https://store.taylorswift.com/cdn/shop/files/O73QTDS5VSflLTLknSb2abCO1hkUUmwd-2_600x.png?v=1707100357',
1, 3, 1, 0), 

('Billie Eilish - Happier Than Ever', 16.49, 333, 
'Tracklist
A-Side
1. Getting Older
2. I Didn''t Change My Number
3. Billie Bossa Nova
4. my future
5. Oxytocin
6. GOLDWING
7. Lost Cause
8. Halley''s Comet

B-Side
1. Not My Responsibility
2. OverHeated
3. Everybody Dies
4. Your Power
5. NDA
6. Therefore I Am
7. Happier Than Ever
8. Male Fantasy',
'https://img.thetedellis.com/v7/thetedellis.com/wp-content/uploads/2021/04/Billie-Eilish-Happier-Than-Ever-Cassette.jpg?org_if_sml=0',
1, 3, 1, 0)


INSERT INTO Users VALUES 
('hl14toi','VNHL','0140140140','hl14toi@gmail.com',0,N'Thủ Đức','14tymuonnam','1969-12-18',1,
'', N'Long Phước, Quận 9'),

('taolatxd','Tranh Nguyen','0937615904','tranhnguyen03@gmail.com',2,N'Quảng Nam','hello123','2003-08-06',1,
'', N'Hoa Mỹ, Đại Nghĩa, Đại Lộc'), 

('toiyeuvn','Hang Nguyen Vo Thuy','0987654321','heng@gmail.com',1,'Da Nang','hoilamgi','2004-08-17',0,
'https://bizweb.dktcdn.net/100/438/408/files/meme-doi-quan-yodyvn4.jpg?v=1687318667723',
N'149 Núi Thành, Hoà Cường Bắc, Hải Châu'), 

('thuswift',N'Quá Nhanh Quá Nguy Hiểm','1234567890','abc@gmail.com',0,N'Bình Dương','abc123','2001-03-13',1,
'', N'Đ. NI14, Thới Hoà, Bến Cát'), 

('thuxidau','Thu Doan','0305070901','thudoan@fpt.edu.vn',1,'Hanoi','123321','2004-09-02',0,
'./img/avatar/myavt.jpg', N'10 Khuong Ha, Khuong Dinh, Thanh Xuan'), 

('triduc','Le Phan Tri Duc','0806040200','ducklee@gmail.com',0,N'Hải Phòng','123','2005-12-06',1,
'', N'60D P. Phan Bội Châu, Quang Trung, Hồng Bàng')