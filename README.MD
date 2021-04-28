## Bowling

1. Execute BowlingGame.java
<pre>
Please, insert your bowling score:
</pre>
2. Paste your score:
<pre>
Jeff    10
John    3
John    7
Jeff    7
Jeff    3
John    6
John    3
Jeff    9
Jeff    0
John    10
Jeff    10
John    8
John    1
Jeff    0
Jeff    8
John    10
Jeff    8
Jeff    2
John    10
Jeff    F
Jeff    6
John    9
John    0
Jeff    10
John    7
John    3
Jeff    10
John    4
John    4
Jeff    10
Jeff    8
Jeff    1
John    10
John    9
John    0
</pre>

3. Then hit enter twice
4. Output
<pre>
Frame		1		2		3		4		5		6		7		8		9		10		
Jeff
Pinfalls	 	X	7	/	9	-	 	X	-	8	8	/	F	6	 	X	 	X	 	X	
Score		20		39		48		66		74		84		90		120		148		167		

Frame		1		2		3		4		5		6		7		8		9		10		
John
Pinfalls	3	/	6	-	 	X	8	-	 	X	 	X	9	-	7	/	4	-	 	X	
Score		16		25		44		53		82		101		110		124		132		151		

</pre>

### Example: Perfect Game

#### Input

<pre>
John    10
John    10
John    10
John    10
John    10
John    10
John    10
John    10
John    10
John    10
John    10
John    10

</pre>

#### Output

<pre>

Frame		1		2		3		4		5		6		7		8		9		10		
John
Pinfalls	 	X	 	X	 	X	 	X	 	X	 	X	 	X	 	X	 	X	 	X	
Score		30		60		90		120		150		180		210		240		270		300		

</pre>

### Example: Worst Game

#### Input

<pre>
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
John    0
</pre>

#### Output

<pre>
Frame		1		2		3		4		5		6		7		8		9		10		
John
Pinfalls	-	0	-	0	-	0	-	0	-	0	-	0	-	0	-	0	-	0	-	0	
Score		0		0		0		0		0		0		0		0		0		0		

</pre>
