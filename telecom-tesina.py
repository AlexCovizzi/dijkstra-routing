import pprint

def indmin(minimo, nodo, indiceMinimo, indiceNodo):
    if(minimo <= nodo):
        return indiceMinimo
    else:
        return indiceNodo
        
def min(a, b):
    if a <= b:
        return a
    else:
        return b

print("Quanti nodi?")
nodi = int(raw_input())
rete = [[0 for i in range(nodi)] for j in range(nodi)]

for i in range(nodi):
    for j in range(i+1, nodi):
        print("Inserisci il peso [" + str(i) + "][" + str(j) + "]:")
        rete[i][j] = int(raw_input())
        rete[j][i] = rete[i][j]

for i in range(nodi):
    pprint.pprint(rete[i])

print("Quale nodo scegliere come riferimento? Scegliere tra 0 e "
+ str(nodi - 1))

F = []

F.append(int(raw_input()))

centro = F[0]

for i in range(nodi - 1):
    minimo = 100000
    indiceMinimo = -1
    
    for j in range(nodi):
        if not j in F:
            indiceMinimo = indmin(minimo, rete[centro][j], indiceMinimo, j)
            minimo = rete[centro][indiceMinimo]
        
    if indiceMinimo != -1:
        F.append(indiceMinimo)

    for j in range(nodi):
        if not j in F:
            rete[centro][j] = min(rete[centro][j],
rete[centro][F[i+1]] + rete[F[i+1]][j])

print("La lista dei nodi selezionati")
pprint.pprint(F)

print("Le distanza finali tra il riferimento e i nodi")
pprint.pprint(rete[F[centro]])