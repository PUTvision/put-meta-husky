#
# This file is the meta-husky recipe.
#
SUMMARY = "Husky_msgs robot library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "husky"
ROS_BPN = "husky-msgs"

ROS_BUILD_DEPENDS = " \
    builtin-interfaces \
    std-msgs \
    rosidl-default-generators \
    rosidl-adapter \
    ros-environment \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \ 
    ament-cmake-ros \
    ament-cmake-gmock \
    ament-cmake-gtest \
    ament-cmake-pytest \
"

ROS_EXPORT_DEPENDS = " \
    rosidl-default-runtime \
    std-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    builtin-interfaces \
    rosidl-default-runtime \
    std-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

# No module named rosidl_adapter
export PYTHONPATH
PYTHONPATH = "/home/put/.local/lib/python3.9/site-packages:/opt/ros/foxy/lib/python3.8/site-packages"

#
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# # Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# # don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"
#
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

# ERROR: husky-msgs-1.0-r0 do_package_qa: QA Issue: 
# -dev package husky-msgs-dev contains non-symlink .so '/usr/lib/libhusky_msgs__rosidl_typesupport_cpp.so'
# -dev package husky-msgs-dev contains non-symlink .so '/usr/lib/libhusky_msgs__rosidl_typesupport_introspection_cpp.so'
# -dev package husky-msgs-dev contains non-symlink .so '/usr/lib/libhusky_msgs__rosidl_generator_c.so'
# -dev package husky-msgs-dev contains non-symlink .so '/usr/lib/libhusky_msgs__rosidl_typesupport_introspection_c.so'
# -dev package husky-msgs-dev contains non-symlink .so '/usr/lib/libhusky_msgs__python.so'
# -dev package husky-msgs-dev contains non-symlink .so '/usr/lib/libhusky_msgs__rosidl_typesupport_fastrtps_cpp.so'
# -dev package husky-msgs-dev contains non-symlink .so '/usr/lib/libhusky_msgs__rosidl_typesupport_fastrtps_c.so'
# -dev package husky-msgs-dev contains non-symlink .so '/usr/lib/libhusky_msgs__rosidl_typesupport_c.so' [dev-elf]
FILES:${PN}:prepend = " \
    ${datadir}/husky_msgs \
    /usr/lib/python3.9/site-packages/husky_msgs \
"

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/lib*${SOLIBSDEV} \
"

ROS_BRANCH ?= "branch=put-foxy"
SRC_URI = "git://github.com/PPI-PUT/put-husky;${ROS_BRANCH};protocol=https"
SRCREV = "4305af80fe25b2821d6f671a8f4f573e32b61d01"

S = "${WORKDIR}/git/husky_msgs"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_insane_dev_so
inherit ros_${ROS_BUILD_TYPE}
