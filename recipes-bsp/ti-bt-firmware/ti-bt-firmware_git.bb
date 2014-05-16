# Copyright (C) 2014 Johan Hovold <johan@hovoldconsulting.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "TI WiLink Bluetooth Firmware"
HOMEPAGE = "https://github.com/TI-ECS/bt-firmware"
LICENSE = "Firmware-ti-bluetooth"
SECTION = "network/misc"
PR = "r0+gitr${SRCPV}"

LIC_FILES_CHKSUM = " \
    file://am335x/LICENCE;md5=1c9961176d6529283e0d0c983be41b45 \
    "

SRCREV = "2b10c6ebd7c08d849a92e3e993cec0ebac38c7c6"
SRC_URI = "git://github.com/TI-ECS/bt-firmware.git;protocol=git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Limited to ti33x for now
COMPATIBLE_MACHINE = "ti33x"

S = "${WORKDIR}/git"

do_compile() {
    :
}

# Only provide TIInit_7.6.15 for now now.
#
# NOTE: TIInit_7.2.31.bts is also provided by, and thus conflicts with,
#       linux-firmware
do_install() {
    install -d ${D}/${base_libdir}/firmware
    install -m 644 ${S}/am335x/TIInit_7.6.15.bts ${D}/${base_libdir}/firmware
    install -m 644 ${S}/am335x/LICENCE \
        ${D}/${base_libdir}/firmware/LICENCE.TIInit
}

FILES_${PN} += "${base_libdir}/firmware"
