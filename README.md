trabalho referente a materia estrutura de dados 2023/2
O objetivo do trabalho é criar um programa que faça o processamento de um texto, mostrando seus principais topicos
dentro de um grafo, demostrar coautoria entre autores e comparaçoes entre os texto. 
O programa foi sem o uso de nenhuma api ou biblioteca externa do java.

A pasta autores é onde estão os textos usados no programa
O arquivo index.java é o responsavel por mostrar o principais topicos de cadas texto
tambem é responsavel por fazer as comparaçoes entre os texto
O arquivo autores.java é o responsavel por mostra a relação entre os autores dos textos.
Todos os resultados são mostrados no terminal.

Gabriel Lourenço Santos Ra:822164041

Uma breve analise assintótica do programa

Para ir direto ao ponto o termo dominante do programa seria n2(n elevado ao quabrado) 
Ele se encaixa como n2 porque dentro do programa a alguns for dentro de outro for, mesmo que que seja imposivel todos esse for dentro de outro rodarem ao mesmo tempo.
Sobre o melhor caso, pior caso e caso medio, o melhor caso é obivimente aquele em que programa não roda pois dessa maneira a maior parte das linhas não vai ser excutada
Agora falando dos casos em que o programa roda corretamente, todos os casos são parecidos.
Por exemplo na parte dos autores, ele sempre vai ler todos os autores, e fazer todas comparaçoes para ver se existe uma relação, 
a unica difereça entre os caos é que uma linha de print vai ser acionada, e em outra não.
O mesmo pode ser aplicado ao compara para ver se os texto são semelantes, vai ser sempre comparada todos os elementos de um texto com todos od outro.
A maior diferença é em mostra as principais palavras do textos, ja que essa parte depende bastante da quantida de palavras de um texto, nesse caso o melhor caso é sempre aquele que o texto é pequeno, e pior caso um texto gigante de 10.000 palavras, mas uma vez que o programa é feito para ler resumos, mesmo que eles não tenha tamanho fixo dentre os texto apresentados, não existe uma grande variação entre seu tamanho.
Em suma o programa depende mais do tamanho do array, que no caso são quantos textos iram ser avaliados, do que tratar cada um dos textos de maneira diferente.
