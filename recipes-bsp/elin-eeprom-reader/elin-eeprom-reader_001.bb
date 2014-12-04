DESCRIPTION = "ELIN-W16 EEPROM reader"
LICENSE = "CLOSED"
SECTION = "ublox/util"
PR = "r0"

# Must be run before networking starts as wlan driver requires nvs file
INITSCRIPT_NAME = "elin-w16-radio-params"
INITSCRIPT_PARAMS = "defaults 00"

inherit autotools update-rc.d

SRC_URI = " \
	git://${UBLOX_GIT}/elin-utils.git;protocol=${UBLOX_GIT_PROTOCOL};gitdir=${WORKDIR}\elin \
	file://elin-w16-radio-params \
    "

SRCREV="e7c175ab1174cd8f9dbc1c57b5bfb6c3650de3e9"

EXTRA_OEMAKE = " -C ${WORKDIR}/git/elin-eeprom 'DESTDIR=${WORKDIR}' \
        'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' \
        'CFLAGS=${CFLAGS} -I${S}/include -DWITHOUT_XATTR' 'BUILDDIR=${S}'"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
	oe_runmake elin-eeprom
}

do_install() {

	# Install ELIN-W16 eeprom reader init script
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/elin-w16-radio-params ${D}${sysconfdir}/init.d/elin-w16-radio-params

	# Install ELIN-W16 eeprom reader
	install -d ${D}${sbindir}
	install -m 0755 ${WORKDIR}/git/elin-eeprom/elin-eeprom ${D}${sbindir}
}
