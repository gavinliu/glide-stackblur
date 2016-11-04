# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)

add_library( StackBlur

             SHARED

             src/main/cpp/StackBlur.c )

find_library( log-lib
              log )

find_library( m-lib
              m )

find_library( jnigraphics-lib
              jnigraphics )

target_link_libraries( StackBlur
                       ${log-lib}
                       ${m-lib}
                       ${jnigraphics-lib} )
