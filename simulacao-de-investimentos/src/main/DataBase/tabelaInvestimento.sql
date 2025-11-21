CREATE TABLE [TESTE].[dbo].[Investimento](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](400) NOT NULL,
	[tipo] [varchar](100) NOT NULL,
	[rentabilidade] [float] NOT NULL,
	[risco] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

insert into [TESTE].[dbo].[Investimento]
([nome],[tipo],[rentabilidade],[risco])
values
('CDB Banco Alfa', 'Renda Fixa', 0.12, 'Baixo'),
('Tesouro Selic 2029', 'Tesouro Direto', 0.10, 'Baixo'),
('Fundo Imobiliário XPTO', 'FII', 0.08, 'Médio'),
('Ações Petrobras', 'Ações', 0.18, 'Alto'),
('ETF BOVA11', 'ETF', 0.15, 'Médio'),
('Debêntures Energisa', 'Renda Fixa', 0.11, 'Médio'),
('Fundo Multimercado Atlas', 'Fundo', 0.20, 'Alto'),
('Criptomoeda BTC', 'Criptoativo', 0.30, 'Muito Alto'),
('LCI Banco Beta', 'Renda Fixa', 0.09, 'Baixo'),
( 'Fundo de Ações Tech Growth', 'Fundo', 0.22, 'Alto');

select * from [TESTE].[dbo].[Investimento]

CREATE SEQUENCE Investimento_SEQ START WITH 1 INCREMENT BY 1;