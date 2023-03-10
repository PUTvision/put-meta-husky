#
# This file is the meta-husky recipe.
#
inherit ros_distro_foxy

SUMMARY = "Husky_base robot library"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"

ROS_CN = "husky"
ROS_BPN = "husky-base"

ROS_BUILD_DEPENDS = " \
    controller-interface \
    controller-manager \
    husky-msgs \
    nav-msgs \
    std-srvs \
    tf2 \
    tf2-bullet \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    std-srvs \
    tf2 \
    interactive-marker-twist-server \
    twist-mux \
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


# ERROR: husky-base-1.0-r0 do_package: QA Issue: husky-base: Files/directories were installed but not shipped in any package:                                                                                        
#   /usr/share/husky_base
FILES:${PN}:prepend = " \
    ${datadir}/husky_base \
"

# ERROR: husky-base-1.0-r0 do_package_qa: QA Issue: -dev package husky-base-dev contains non-symlink .so '/usr/lib/libhusky_hardware.so' [dev-elf]
FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/lib*${SOLIBSDEV} \
"

ROS_BRANCH ?= "branch=put-foxy"
SRC_URI = "git://github.com/PPI-PUT/put-husky;${ROS_BRANCH};protocol=https \
            file://put-husky-autostart.bash \
            file://put-husky-autostart.service"

SRCREV = "4305af80fe25b2821d6f671a8f4f573e32b61d01"

S = "${WORKDIR}/git/husky_base"


ROS_BUILD_TYPE = "ament_cmake"


inherit ros_${ROS_BUILD_TYPE}



inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "put-husky-autostart.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/put-husky-autostart.bash ${D}${bindir}

	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/put-husky-autostart.service ${D}${systemd_system_unitdir}
}

FILES:${PN} += "${systemd_system_unitdir}"

