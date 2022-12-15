#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "Husky_description robot library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "husky"
ROS_BPN = "husky-description"

ROS_BUILD_DEPENDS = " \
    urdf \
    xacro \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = ""

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""
#
DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# # Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# # don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"
#
RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"


FILES:${PN}:prepend = " \
    ${datadir}/husky_description \
"
FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/lib*${SOLIBSDEV} \
"

ROS_BRANCH ?= "branch=foxy-devel"
SRC_URI = "git://github.com/husky/husky;${ROS_BRANCH};protocol=https"
SRCREV = "ffafe25ae2b4c156e70fd3d84cea9d9aa86081cb"

S = "${WORKDIR}/git/husky_description"


ROS_BUILD_TYPE = "ament_cmake"


inherit ros_${ROS_BUILD_TYPE}
