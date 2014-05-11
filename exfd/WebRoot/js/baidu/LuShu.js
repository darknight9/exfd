/**
 * @fileoverview ç™¾åº¦åœ°å›¾çš„è½¨è¿¹è·Ÿéšç±»ï¼Œå¯¹å¤–å¼€æ”¾ã€‚
 * ç”¨æˆ·å¯ä»¥åœ¨åœ°å›¾ä¸Šè‡ªå®šä¹‰è½¨è¿¹è¿åŠ¨
 * å¯ä»¥è‡ªå®šä¹‰è·¯è¿‡æŸä¸ªç‚¹çš„å›¾ç‰‡ï¼Œæ–‡å­—ä»‹ç»ç­‰ã€‚
 * ä¸»å…¥å£ç±»æ˜¯<a href="symbols/BMapLib.LuShu.html">LuShu</a>ï¼Œ
 * åŸºäºŽBaidu Map API 1.2ã€‚.
 *
 * @author Baidu Map Api Group
 * @version 1.2
 */

/**
 * @namespace BMapçš„æ‰€æœ‰libraryç±»å‡æ”¾åœ¨BMapLibå‘½åç©ºé—´ä¸‹
 */
var BMapLib = window.BMapLib = BMapLib || {};

(function() {
    //å£°æ˜ŽbaiduåŒ…
    var T, baidu = T = baidu || {version: '1.5.0'};
    baidu.guid = '$BAIDU$';
    //ä»¥ä¸‹æ–¹æ³•ä¸ºç™¾åº¦Tangramæ¡†æž¶ä¸­çš„æ–¹æ³•ï¼Œè¯·åˆ°http://tangram.baidu.com æŸ¥çœ‹æ–‡æ¡£
    (function() {
        window[baidu.guid] = window[baidu.guid] || {};
        baidu.dom = baidu.dom || {};
        baidu.dom.g = function(id) {
            if ('string' == typeof id || id instanceof String) {
                return document.getElementById(id);
            } else if (id && id.nodeName && (id.nodeType == 1 || id.nodeType == 9)) {
                return id;
            }
            return null;
        };
        baidu.g = baidu.G = baidu.dom.g;
        baidu.lang = baidu.lang || {};
        baidu.lang.isString = function(source) {
            return '[object String]' == Object.prototype.toString.call(source);
        };
        baidu.isString = baidu.lang.isString;
        baidu.dom._g = function(id) {
            if (baidu.lang.isString(id)) {
                return document.getElementById(id);
            }
            return id;
        };
        baidu._g = baidu.dom._g;
        baidu.dom.getDocument = function(element) {
            element = baidu.dom.g(element);
            return element.nodeType == 9 ? element : element.ownerDocument || element.document;
        };
        baidu.browser = baidu.browser || {};
        baidu.browser.ie = baidu.ie = /msie (\d+\.\d+)/i.test(navigator.userAgent) ? (document.documentMode || + RegExp['\x241']) : undefined;
        baidu.dom.getComputedStyle = function(element, key) {
            element = baidu.dom._g(element);
            var doc = baidu.dom.getDocument(element),
                styles;
            if (doc.defaultView && doc.defaultView.getComputedStyle) {
                styles = doc.defaultView.getComputedStyle(element, null);
                if (styles) {
                    return styles[key] || styles.getPropertyValue(key);
                }
            }
            return '';
        };
        baidu.dom._styleFixer = baidu.dom._styleFixer || {};
        baidu.dom._styleFilter = baidu.dom._styleFilter || [];
        baidu.dom._styleFilter.filter = function(key, value, method) {
            for (var i = 0, filters = baidu.dom._styleFilter, filter; filter = filters[i]; i++) {
                if (filter = filter[method]) {
                    value = filter(key, value);
                }
            }
            return value;
        };
        baidu.string = baidu.string || {};


        baidu.string.toCamelCase = function(source) {

            if (source.indexOf('-') < 0 && source.indexOf('_') < 0) {
                return source;
            }
            return source.replace(/[-_][^-_]/g, function(match) {
                return match.charAt(1).toUpperCase();
            });
        };
        baidu.dom.getStyle = function(element, key) {
            var dom = baidu.dom;
            element = dom.g(element);
            key = baidu.string.toCamelCase(key);

            var value = element.style[key] ||
                        (element.currentStyle ? element.currentStyle[key] : '') ||
                        dom.getComputedStyle(element, key);

            if (!value) {
                var fixer = dom._styleFixer[key];
                if (fixer) {
                    value = fixer.get ? fixer.get(element) : baidu.dom.getStyle(element, fixer);
                }
            }

            if (fixer = dom._styleFilter) {
                value = fixer.filter(key, value, 'get');
            }
            return value;
        };
        baidu.getStyle = baidu.dom.getStyle;
        baidu.dom._NAME_ATTRS = (function() {
            var result = {
                'cellpadding': 'cellPadding',
                'cellspacing': 'cellSpacing',
                'colspan': 'colSpan',
                'rowspan': 'rowSpan',
                'valign': 'vAlign',
                'usemap': 'useMap',
                'frameborder': 'frameBorder'
            };

            if (baidu.browser.ie < 8) {
                result['for'] = 'htmlFor';
                result['class'] = 'className';
            } else {
                result['htmlFor'] = 'for';
                result['className'] = 'class';
            }

            return result;
        })();
        baidu.dom.setAttr = function(element, key, value) {
            element = baidu.dom.g(element);
            if ('style' == key) {
                element.style.cssText = value;
            } else {
                key = baidu.dom._NAME_ATTRS[key] || key;
                element.setAttribute(key, value);
            }
            return element;
        };
        baidu.setAttr = baidu.dom.setAttr;
        baidu.dom.setAttrs = function(element, attributes) {
            element = baidu.dom.g(element);
            for (var key in attributes) {
                baidu.dom.setAttr(element, key, attributes[key]);
            }
            return element;
        };
        baidu.setAttrs = baidu.dom.setAttrs;
        baidu.dom.create = function(tagName, opt_attributes) {
            var el = document.createElement(tagName),
                attributes = opt_attributes || {};
            return baidu.dom.setAttrs(el, attributes);
        };
        baidu.object = baidu.object || {};
        baidu.extend =
        baidu.object.extend = function(target, source) {
            for (var p in source) {
                if (source.hasOwnProperty(p)) {
                    target[p] = source[p];
                }
            }
            return target;
        };
    })();

    /**
     * @exports LuShu as BMapLib.LuShu
     */
    var LuShu =
    /**
     * LuShuç±»çš„æž„é€ å‡½æ•°
     * @class LuShu <b>å…¥å£</b>ã€‚
     * å®žä¾‹åŒ–è¯¥ç±»åŽï¼Œå¯è°ƒç”¨,start,end,pauseç­‰æ–¹æ³•æŽ§åˆ¶è¦†ç›–ç‰©çš„è¿åŠ¨ã€‚

     * @constructor
         * @param {Map} map Baidu mapçš„å®žä¾‹å¯¹è±¡.
         * @param {Array} path æž„æˆè·¯çº¿çš„pointçš„æ•°ç»„.
         * @param {Json Object} opts å¯é€‰çš„è¾“å…¥å‚æ•°ï¼Œéžå¿…å¡«é¡¹ã€‚å¯è¾“å…¥é€‰é¡¹åŒ…æ‹¬ï¼š<br />
         * {<br />"<b>landmarkPois</b>" : {Array} è¦åœ¨è¦†ç›–ç‰©ç§»åŠ¨è¿‡ç¨‹ä¸­ï¼Œæ˜¾ç¤ºçš„ç‰¹æ®Šç‚¹ã€‚æ ¼å¼å¦‚ä¸‹:landmarkPois:[<br />
         *      {lng:116.314782,lat:39.913508,html:'åŠ æ²¹ç«™',pauseTime:2},<br />
         *      {lng:116.315391,lat:39.964429,html:'é«˜é€Ÿå…¬è·¯æ”¶è´¹ç«™,pauseTime:3}]<br />
         * <br />"<b>icon</b>" : {Icon} è¦†ç›–ç‰©çš„icon,
         * <br />"<b>speed</b>" : {Number} è¦†ç›–ç‰©ç§»åŠ¨é€Ÿåº¦ï¼Œå•ä½ç±³/ç§’    <br />
         * <br />"<b>defaultContent</b>" : {String} è¦†ç›–ç‰©ä¸­çš„å†…å®¹    <br />
         * }<br />.
         * @example <b>å‚è€ƒç¤ºä¾‹ï¼š</b><br />
         * var lushu = new BMapLib.LuShu(map,arrPois,{defaultContent:"ä»ŽåŒ—äº¬åˆ°å¤©æ´¥",landmarkPois:[]});
     */
     BMapLib.LuShu = function(map, path, opts) {
        if (!path || path.length < 1) {
            return;
        }
        this._map = map;
        //å­˜å‚¨ä¸€æ¡è·¯çº¿
        this._path = path;
        //ç§»åŠ¨åˆ°å½“å‰ç‚¹çš„ç´¢å¼•
        this.i = 0;
        //æŽ§åˆ¶æš‚åœåŽå¼€å§‹ç§»åŠ¨çš„é˜Ÿåˆ—çš„æ•°ç»„
        this._setTimeoutQuene = [];
        //è¿›è¡Œåæ ‡è½¬æ¢çš„ç±»
        this._projection = this._map.getMapType().getProjection();
        this._opts = {
            icon: null,
            //é»˜è®¤é€Ÿåº¦ ç±³/ç§’
            speed: 4000,
            defaultContent: ''
        };
        this._setOptions(opts);

        //å¦‚æžœä¸æ˜¯é»˜è®¤å®žä¾‹ï¼Œåˆ™ä½¿ç”¨é»˜è®¤çš„icon
        if (!this._opts.icon instanceof BMap.Icon) {
            this._opts.icon = defaultIcon;
        }
    }
     /**
     * æ ¹æ®ç”¨æˆ·è¾“å…¥çš„optsï¼Œä¿®æ”¹é»˜è®¤å‚æ•°_opts
     * @param {Json Object} opts ç”¨æˆ·è¾“å…¥çš„ä¿®æ”¹å‚æ•°.
     * @return æ— è¿”å›žå€¼.
     */
    LuShu.prototype._setOptions = function(opts) {
        if (!opts) {
            return;
        }
        for (var p in opts) {
            if (opts.hasOwnProperty(p)) {
                this._opts[p] = opts[p];
            }
        }
    }

    /**
     * @description å¼€å§‹è¿åŠ¨
     * @param none
     * @return æ— è¿”å›žå€¼.
     *
     * @example <b>å‚è€ƒç¤ºä¾‹ï¼š</b><br />
     * lushu.start();
     */
    LuShu.prototype.start = function() {
        var me = this,
            len = me._path.length;
        //ä¸æ˜¯ç¬¬ä¸€æ¬¡ç‚¹å‡»å¼€å§‹,å¹¶ä¸”å°è½¦è¿˜æ²¡åˆ°è¾¾ç»ˆç‚¹
        if (me.i && me.i < len - 1) {
            //æ²¡æŒ‰pauseå†æŒ‰startä¸åšå¤„ç†
            if (!me._fromPause) {
                return;
            }else if(!me._fromStop){
	            //æŒ‰äº†pauseæŒ‰é’®,å¹¶ä¸”å†æŒ‰startï¼Œç›´æŽ¥ç§»åŠ¨åˆ°ä¸‹ä¸€ç‚¹
	            //å¹¶ä¸”æ­¤è¿‡ç¨‹ä¸­ï¼Œæ²¡æœ‰æŒ‰stopæŒ‰é’®
	            //é˜²æ­¢å…ˆstopï¼Œå†pauseï¼Œç„¶åŽè¿žç»­ä¸åœçš„startçš„å¼‚å¸¸
	            me._moveNext(++me.i);
            }
        }else {
            //ç¬¬ä¸€æ¬¡ç‚¹å‡»å¼€å§‹ï¼Œæˆ–è€…ç‚¹äº†stopä¹‹åŽç‚¹å¼€å§‹
            me._addMarker();
            //ç­‰å¾…markeråŠ¨ç”»å®Œæ¯•å†åŠ è½½infowindow
            me._timeoutFlag = setTimeout(function() {
                    me._addInfoWin();
                    me._moveNext(me.i);
            },400);
        }
         //é‡ç½®çŠ¶æ€
        this._fromPause = false;
        this._fromStop = false;
    },
    /**
     * ç»“æŸè¿åŠ¨
     * @return æ— è¿”å›žå€¼.
     *
     * @example <b>å‚è€ƒç¤ºä¾‹ï¼š</b><br />
     * lushu.stop();
     */
    LuShu.prototype.stop = function() {
        this.i = 0;
        this._fromStop = true;
        clearInterval(this._intervalFlag);
        this._clearTimeout();
        //é‡ç½®landmarké‡Œè¾¹çš„poiä¸ºæœªæ˜¾ç¤ºçŠ¶æ€
        for (var i = 0, t = this._opts.landmarkPois, len = t.length; i < len; i++) {
            t[i].bShow = false;
        }
    };
    /**
     * æš‚åœè¿åŠ¨
     * @return æ— è¿”å›žå€¼.
     */
    LuShu.prototype.pause = function() {
        clearInterval(this._intervalFlag);

        //æ ‡è¯†æ˜¯å¦æ˜¯æŒ‰è¿‡pauseæŒ‰é’®
        this._fromPause = true;
        this._clearTimeout();
    };
    /**
     * éšè—ä¸Šæ–¹overlay
     * @return æ— è¿”å›žå€¼.
     *
     * @example <b>å‚è€ƒç¤ºä¾‹ï¼š</b><br />
     * lushu.hideInfoWindow();
     */
    LuShu.prototype.hideInfoWindow = function() {
        this._overlay._div.style.visibility = 'hidden';
    };
    /**
     * æ˜¾ç¤ºä¸Šæ–¹overlay
     * @return æ— è¿”å›žå€¼.
     *
     * @example <b>å‚è€ƒç¤ºä¾‹ï¼š</b><br />
     * lushu.showInfoWindow();
     */
    LuShu.prototype.showInfoWindow = function() {
        this._overlay._div.style.visibility = 'visible';
    };
    //Lushuç§æœ‰æ–¹æ³•
    baidu.object.extend(LuShu.prototype, {
        /**
         * æ·»åŠ markeråˆ°åœ°å›¾ä¸Š
         * @param {Function} å›žè°ƒå‡½æ•°.
         * @return æ— è¿”å›žå€¼.
         */
        _addMarker: function(callback) {
            if (this._marker) {
                this.stop();
                this._map.removeOverlay(this._marker);
                clearTimeout(this._timeoutFlag);
            }
            //ç§»é™¤ä¹‹å‰çš„overlay
            this._overlay && this._map.removeOverlay(this._overlay);
            var marker = new BMap.Marker(this._path[0]);
            this._opts.icon && marker.setIcon(this._opts.icon);
            this._map.addOverlay(marker);
            marker.setAnimation(BMAP_ANIMATION_DROP);
            this._marker = marker;
        },
        /**
         * æ·»åŠ ä¸Šæ–¹overlay
         * @return æ— è¿”å›žå€¼.
         */
        _addInfoWin: function() {
            var me = this;
            var overlay = new CustomOverlay(me._marker.getPosition(), me._opts.defaultContent);

            //å°†å½“å‰ç±»çš„å¼•ç”¨ä¼ ç»™overlayã€‚
            overlay.setRelatedClass(this);
            this._overlay = overlay;
            this._map.addOverlay(overlay);
        },
        /**
         * èŽ·å–å¢¨å¡æ‰˜åæ ‡
         * @param {Point} poi ç»çº¬åº¦åæ ‡.
         * @return æ— è¿”å›žå€¼.
         */
        _getMercator: function(poi) {
            return this._map.getMapType().getProjection().lngLatToPoint(poi);
        },
        /**
         * è®¡ç®—ä¸¤ç‚¹é—´çš„è·ç¦»
         * @param {Point} poi ç»çº¬åº¦åæ ‡Aç‚¹.
         * @param {Point} poi ç»çº¬åº¦åæ ‡Bç‚¹.
         * @return æ— è¿”å›žå€¼.
         */
        _getDistance: function(pxA, pxB) {
            return Math.sqrt(Math.pow(pxA.x - pxB.x, 2) + Math.pow(pxA.y - pxB.y, 2));
        },
          //ç›®æ ‡ç‚¹çš„  å½“å‰çš„æ­¥é•¿,position,æ€»çš„æ­¥é•¿,åŠ¨ç”»æ•ˆæžœ,å›žè°ƒ
        /**
         * ç§»åŠ¨å°è½¦
         * @param {Number} poi å½“å‰çš„æ­¥é•¿.
         * @param {Point} initPos ç»çº¬åº¦åæ ‡åˆå§‹ç‚¹.
         * @param {Point} targetPos ç»çº¬åº¦åæ ‡ç›®æ ‡ç‚¹.
         * @param {Function} effect ç¼“åŠ¨æ•ˆæžœ.
         * @return æ— è¿”å›žå€¼.
         */
        _move: function(initPos,targetPos,effect) {
            var me = this,
                //å½“å‰çš„å¸§æ•°
                currentCount = 0,
                //æ­¥é•¿ï¼Œç±³/ç§’
                timer = 10,
                step = this._opts.speed / (1000 / timer),
                //åˆå§‹åæ ‡
                initPos = this._projection.lngLatToPoint(initPos),
                //èŽ·å–ç»“æŸç‚¹çš„(x,y)åæ ‡
                targetPos = this._projection.lngLatToPoint(targetPos),
                //æ€»çš„æ­¥é•¿
                count = Math.round(me._getDistance(initPos, targetPos) / step);

            //å¦‚æžœå°äºŽ1ç›´æŽ¥ç§»åŠ¨åˆ°ä¸‹ä¸€ç‚¹
            if (count < 1) {
                me._moveNext(++me.i);
                return;
            }
            //ä¸¤ç‚¹ä¹‹é—´åŒ€é€Ÿç§»åŠ¨
            me._intervalFlag = setInterval(function() {
            //ä¸¤ç‚¹ä¹‹é—´å½“å‰å¸§æ•°å¤§äºŽæ€»å¸§æ•°çš„æ—¶å€™ï¼Œåˆ™è¯´æ˜Žå·²ç»å®Œæˆç§»åŠ¨
	            if (currentCount >= count) {
	                clearInterval(me._intervalFlag);
	                //ç§»åŠ¨çš„ç‚¹å·²ç»è¶…è¿‡æ€»çš„é•¿åº¦
		        	if(me.i > me._path.length){
						return;
		        	}
		        	//è¿è¡Œä¸‹ä¸€ä¸ªç‚¹
	                me._moveNext(++me.i);
	            }else {
	                    //æ­£åœ¨ç§»åŠ¨
	                    currentCount++;
	                    var x = effect(initPos.x, targetPos.x, currentCount, count),
	                        y = effect(initPos.y, targetPos.y, currentCount, count),
	                        pos = me._projection.pointToLngLat(new BMap.Pixel(x, y));
	                    //è®¾ç½®marker
	                    me._marker.setPosition(pos);
	                    //è®¾ç½®è‡ªå®šä¹‰overlayçš„ä½ç½®
	                    me._setInfoWin(pos);
	                }
	        },timer);
        },
        /**
         * ç§»åŠ¨åˆ°ä¸‹ä¸€ä¸ªç‚¹
         * @param {Number} index å½“å‰ç‚¹çš„ç´¢å¼•.
         * @return æ— è¿”å›žå€¼.
         */
        _moveNext: function(index) {
            var me = this;
            if (index < this._path.length - 1) {
                me._move(me._path[index], me._path[index + 1], me._tween.linear);
            }
        },
        /**
         * è®¾ç½®å°è½¦ä¸Šæ–¹infowindowçš„å†…å®¹ï¼Œä½ç½®ç­‰
         * @param {Point} pos ç»çº¬åº¦åæ ‡ç‚¹.
         * @return æ— è¿”å›žå€¼.
         */
        _setInfoWin: function(pos) {
            //è®¾ç½®ä¸Šæ–¹overlayçš„position
            var me = this;
            me._overlay.setPosition(pos, me._marker.getIcon().size);
            var index = me._troughPointIndex(pos);
            if (index != -1) {
                clearInterval(me._intervalFlag);
                me._overlay.setHtml(me._opts.landmarkPois[index].html);
                me._overlay.setPosition(pos, me._marker.getIcon().size);
                me._pauseForView(index);
            }else {
                me._overlay.setHtml(me._opts.defaultContent);
            }
        },
        /**
         * åœ¨æŸä¸ªç‚¹æš‚åœçš„æ—¶é—´
         * @param {Number} index ç‚¹çš„ç´¢å¼•.
         * @return æ— è¿”å›žå€¼.
         */
        _pauseForView: function(index) {
            var me = this;
            var t = setTimeout(function() {
                //è¿è¡Œä¸‹ä¸€ä¸ªç‚¹
                me._moveNext(++me.i);
            },me._opts.landmarkPois[index].pauseTime * 1000);
            me._setTimeoutQuene.push(t);
        },
         //æ¸…é™¤æš‚åœåŽå†å¼€å§‹è¿è¡Œçš„timeout
        _clearTimeout: function() {
            for (var i in this._setTimeoutQuene) {
                clearTimeout(this._setTimeoutQuene[i]);
            }
            this._setTimeoutQuene.length = 0;
        },
         //ç¼“åŠ¨æ•ˆæžœ
        _tween: {
            //åˆå§‹åæ ‡ï¼Œç›®æ ‡åæ ‡ï¼Œå½“å‰çš„æ­¥é•¿ï¼Œæ€»çš„æ­¥é•¿
            linear: function(initPos, targetPos, currentCount, count) {
                var b = initPos, c = targetPos - initPos, t = currentCount,
                d = count;
                return c * t / d + b;
            }
        },

        /**
         * å¦ç»è¿‡æŸä¸ªç‚¹çš„index
         * @param {Point} markerPoi å½“å‰å°è½¦çš„åæ ‡ç‚¹.
         * @return æ— è¿”å›žå€¼.
         */
        _troughPointIndex: function(markerPoi) {
            var t = this._opts.landmarkPois, distance;
            for (var i = 0, len = t.length; i < len; i++) {
                //landmarkPoisä¸­çš„ç‚¹æ²¡æœ‰å‡ºçŽ°è¿‡çš„è¯
                if (!t[i].bShow) {
                    distance = this._map.getDistance(new BMap.Point(t[i].lng, t[i].lat), markerPoi);
                    //ä¸¤ç‚¹è·ç¦»å°äºŽ10ç±³ï¼Œè®¤ä¸ºæ˜¯åŒä¸€ä¸ªç‚¹
                    if (distance < 10) {
                        t[i].bShow = true;
                        return i;
                    }
                }
            }
           return -1;
        }
    });
    /**
     * è‡ªå®šä¹‰çš„overlayï¼Œæ˜¾ç¤ºåœ¨å°è½¦çš„ä¸Šæ–¹
     * @param {Point} Point è¦å®šä½çš„ç‚¹.
     * @param {String} html overlayä¸­è¦æ˜¾ç¤ºçš„ä¸œè¥¿.
     * @return æ— è¿”å›žå€¼.
     */
    function CustomOverlay(point,html) {
        this._point = point;
        this._html = html;
    }
    CustomOverlay.prototype = new BMap.Overlay();
    CustomOverlay.prototype.initialize = function(map) {
        var div = this._div = baidu.dom.create('div', {style: 'border:solid 1px #ccc;width:auto;min-width:50px;text-align:center;position:absolute;background:#fff;color:#000;font-size:12px;border-radius: 10px;padding:5px;white-space: nowrap;'});
        div.innerHTML = this._html;
        map.getPanes().floatPane.appendChild(div);
        this._map = map;
        return div;
    }
   CustomOverlay.prototype.draw = function() {
        this.setPosition(this.lushuMain._marker.getPosition(), this.lushuMain._marker.getIcon().size);
    }
    baidu.object.extend(CustomOverlay.prototype, {
        //è®¾ç½®overlayçš„position
        setPosition: function(poi,markerSize) {
            // æ­¤å¤„çš„bugå·²ä¿®å¤ï¼Œæ„Ÿè°¢ è‹—å†¬(diligentcat@gmail.com) çš„ç»†å¿ƒæŸ¥çœ‹å’Œè®¤çœŸæŒ‡å‡º
            var px = this._map.pointToOverlayPixel(poi),
                styleW = baidu.dom.getStyle(this._div, 'width'),
                styleH = baidu.dom.getStyle(this._div, 'height');
                overlayW = parseInt(this._div.clientWidth || styleW, 10),
                overlayH = parseInt(this._div.clientHeight || styleH, 10);
            this._div.style.left = px.x - overlayW / 2 + 'px';
            this._div.style.bottom = -(px.y - markerSize.height) + 'px';
        },
        //è®¾ç½®overlayçš„å†…å®¹
        setHtml: function(html) {
            this._div.innerHTML = html;
        },
        //è·Ÿcustomoverlayç›¸å…³çš„å®žä¾‹çš„å¼•ç”¨
        setRelatedClass: function(lushuMain) {
            this.lushuMain = lushuMain;
        }
    });
})();
