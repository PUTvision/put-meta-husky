#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "robot-upstart library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "husky"
ROS_BPN = "robot-upstart"

ROS_BUILD_DEPENDS = " \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    bash \
    ament-index-python \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""
#
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# # Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# # don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"
#
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

FILES:${PN} += " \
    /usr/share/robot_upstart \
    /usr/share/lib \
    /usr/share/lib/robot_upstart \
"


ROS_BRANCH ?= "branch=foxy-devel"
SRC_URI = "git://github.com/clearpathrobotics/robot_upstart;${ROS_BRANCH};protocol=https"
SRCREV = "271f0560068cc3f4e6dda6585191124a223d90c1"

S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "ament_python"
inherit ros_${ROS_BUILD_TYPE}
