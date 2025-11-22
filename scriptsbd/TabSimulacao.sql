CREATE TABLE [dbo].[Simulacao](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[clienteId] [bigint] NOT NULL,
	[produto] [varchar](800) NULL,
	[tipoProduto] [varchar](200) NULL,
	[rentabilidade] [float] NOT NULL,
	[valorInvestido] [float] NULL,
	[valorFinal] [float] NULL,
	[prazoMeses] [int] NULL,
	[dataSimulacao] [varchar](50) NULL
) ON [PRIMARY]

insert into [Simulacao] 
([clienteId],[produto],[tipoProduto],[rentabilidade],[valorInvestido],[valorFinal],[prazoMeses],[dataSimulacao])
values
(101, 'CDB Banco Alfa', 'Renda Fixa', 0.12, 10000.00, 11200.00, 12, '2025-11-16'),
(102, 'Fundo Imobiliário XPTO', 'FII', 0.08, 25000.00, 27000.00, 24, '2025-11-16'),
(103, 'Ações Petrobras', 'Ações', 0.15, 15000.00, 17250.00, 18, '2025-11-16'),
(104, 'Tesouro Selic 2029', 'Tesouro Direto', 0.10, 5000.00, 5500.00, 6, '2025-11-16'),
(105, 'ETF BOVA11', 'ETF', 0.20, 20000.00, 24000.00, 36, '2025-11-16'),
(101, 'CDB Banco Alfa', 'Renda Fixa', 0.12, 10000.00, 11200.00, 12, '2025-11-16'),
(102, 'Fundo Imobiliário XPTO', 'FII', 0.08, 25000.00, 27000.00, 24, '2025-11-16'),
(103, 'Ações Petrobras', 'Ações', 0.15, 15000.00, 17250.00, 18, '2025-11-16'),
(104, 'Tesouro Selic 2029', 'Tesouro Direto', 0.10, 5000.00, 5500.00, 6, '2025-11-16'),
(105, 'ETF BOVA11', 'ETF', 0.20, 20000.00, 24000.00, 36, '2025-11-16'),
(101, 'CDB Banco Alfa', 'Renda Fixa', 0.12, 10000.00, 11200.00, 12, '2025-11-16'),
(102, 'Fundo Imobiliário XPTO', 'FII', 0.08, 25000.00, 27000.00, 24, '2025-11-16'),
(103, 'Ações Petrobras', 'Ações', 0.15, 15000.00, 17250.00, 18, '2025-11-16'),
(104, 'Tesouro Selic 2029', 'Tesouro Direto', 0.10, 5000.00, 5500.00, 6, '2025-11-16'),
(105, 'ETF BOVA11', 'ETF', 0.20, 20000.00, 24000.00, 36, '2025-11-16'),
(101, 'CDB Banco Alfa', 'Renda Fixa', 0.12, 10000.00, 11200.00, 12, '2025-11-16'),
(102, 'Fundo Imobiliário XPTO', 'FII', 0.08, 25000.00, 27000.00, 24, '2025-11-16'),
(103, 'Ações Petrobras', 'Ações', 0.15, 15000.00, 17250.00, 18, '2025-11-16'),
(104, 'Tesouro Selic 2029', 'Tesouro Direto', 0.10, 5000.00, 5500.00, 6, '2025-11-16'),
(105, 'ETF BOVA11', 'ETF', 0.20, 20000.00, 24000.00, 36, '2025-11-16');
