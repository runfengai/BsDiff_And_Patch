cmake_minimum_required(VERSION 3.4.1)
#bs.c代码执行patch
#add_library(bspatch SHARED
#        src/main/cpp/bs.c
#        src/main/cpp/bspatch.c
#        src/main/cpp/bzip2/blocksort.c
#        src/main/cpp/bzip2/bzip2.c
#        src/main/cpp/bzip2/bzip2recover.c
#        src/main/cpp/bzip2/bzlib.c
#        src/main/cpp/bzip2/compress.c
#        src/main/cpp/bzip2/crctable.c
#        src/main/cpp/bzip2/decompress.c
#        src/main/cpp/bzip2/huffman.c
#        src/main/cpp/bzip2/mk251.c
#        src/main/cpp/bzip2/randtable.c
#        src/main/cpp/bzip2/spewG.c
#        src/main/cpp/bzip2/unzcrash.c
#        )

#bspatch.cpp代码执行patch
add_library(bspatch SHARED
        src/main/cpp/bspatch.cpp
        src/main/cpp/bspatch.c
        src/main/cpp/bzip2/blocksort.c
        src/main/cpp/bzip2/bzip2.c
        src/main/cpp/bzip2/bzip2recover.c
        src/main/cpp/bzip2/bzlib.c
        src/main/cpp/bzip2/compress.c
        src/main/cpp/bzip2/crctable.c
        src/main/cpp/bzip2/decompress.c
        src/main/cpp/bzip2/huffman.c
        src/main/cpp/bzip2/mk251.c
        src/main/cpp/bzip2/randtable.c
        src/main/cpp/bzip2/spewG.c
        src/main/cpp/bzip2/unzcrash.c
        )

find_library(
              log-lib
              log )

target_link_libraries(  bspatch
                        android
                       ${log-lib} )

