algorithm.soj
=====

log
-----
* Date: 11/12/2014  
    create this folder to store the source code for [soj](http://soj.sysu.edu.cn/)  
    
solution
-----
1. water
    * soj1020  
        big integer division
    * soj1021  
        stack
    * soj1035  
    * soj1051  
    * soj1198  
        using strcpy, strcat, strcmp  
    * soj1150  
        ugly, just use BFS.  
    * soj1006  
        stupid, wrong use of strcpy, strcmp, and maybe just compare two string by index  
    * soj1009  
        a big pit! don't use file I/O  
        but you can learn that using fgets and strtok  
        don't use pow, it can't generate long long int, for C, but I haven't been tring it in C++  
2. soj1046
    * be careful for the long description  
        (number of quarters, number of periods that would be returned, minimum quarters)
    * you can regard it as the first n maximum numbers selection, using the heap.  
        maybe faster than selecting after sorting
    * the orderly insert is better than the max n numbers selection by travel the array  
        just hold the limited size of the front n numbers  
3. soj1176
    * abs() is in stdlib.h, not math.h
    * recursive function would be TLE, just mark down the state is OK
4. soj1151
    * contor encoding
    * use list to store the path is too stupid
    * estimate the memory cost by calculate the size of array
5. soj1153
    * dfs
    * A* search
    * too slow, just 0.23s
4. waiting...

