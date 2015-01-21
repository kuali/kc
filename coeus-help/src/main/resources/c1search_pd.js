// global members
var pd_d2hSecondaryWindowsByTopics = null;
function pd_d2hInitSecondaryWindows()
{
    if (pd_d2hSecondaryWindowsByTopics == null)
    {
        pd_d2hSecondaryWindowsByTopics = new Array();
        
    }
}

function pd_newDocContext(strTitle, strData)
{
    var elem = "<html><head>"
    if (strTitle != "")
        elem += "<title>" + strTitle + "</title>";
    elem += "</head><body>";
    elem += strData;
    elem += "</body></html>";
    document.clear();
    document.write(elem);
}

function pd_jSearch(doc, strQuery)
{
    pd_display(doc, pd_jExecQuery(doc, strQuery));
}

function pd_Clause(str){
    var exactPhrase = (str.indexOf(d2hQuotePrefix) == 0 && quotes[str] != null) || (str.toLowerCase().indexOf((searchNotInSpaces + d2hQuotePrefix).toLowerCase()) == 0 && quotes[str.substring(searchNotInSpaces.length, str.length)] != null);
    var parts;
    var foundNot = false;
    if (!exactPhrase)
    {        
        parts = str.split(new RegExp(searchOrInSpaces.replace(/[-[\]{}()*+?.,\\^$|]/g, '\\$&'), "gi"));
        if (parts.length == 1)
        {
            parts = str.split(new RegExp(searchAndInSpaces.replace(/[-[\]{}()*+?.,\\^$|]/g, '\\$&'), "gi"));
            this.type = 2;
        }
        else
            this.type = 1;
        if (parts.length == 1)
        {
            foundNot = str.toUpperCase().indexOf(searchNotInSpaces) == 0;
            if (foundNot)
                str = str.substring(searchNotInSpaces.length, str.length);
            parts = str.split(" ");
            this.type = 2;
        }
        parts = pd_removeRepeatingTerms(parts);
    }
    else
    {    
        foundNot = str.toUpperCase().indexOf(searchNotInSpaces) == 0;
        if (foundNot)
            str = str.substring(searchNotInSpaces.length, str.length);    
        this.type = 0;
        parts = quotes[str].split(" ");
        str = quotes[str];
    }
    if (parts.length > 1 || exactPhrase)
    {
        this.children = new Array(parts.length);
        for (var i = 0; i < parts.length; i++)
        {
            this.children[i] = new pd_Clause(parts[i]);
            if (exactPhrase)
                this.children[i].type = 0;
        }
    }
    if (foundNot)
    {
        if (parts.length == 1 || exactPhrase)
            this.not = true;
        else
            this.children[0].not = true;
    }
    this.value = str.toLowerCase();
}

pd_Clause.prototype={
    value:"",
    children:null,
    type:0, //0 - exact phrase, 1 - OR, 2 - AND
    not:false,
    docs:null,
    execute:function(){
        if (this.children == null)
        {
            if (this.type != 0)
            {
                var aliases = pd_getAliases(this.value);
                for (var i = 0; i < aliases.length; i++)
                    this.docs = pd_mergeDocs(this.docs, pd_searchInIndex(aliases[i], true));
            }
            else
                this.docs = pd_searchInIndex(this.value, this.type != 0);
            if (!this.docs && !isEasternLanguage(this.value)) 
            {
                var newString = addSpace(this.value, pd_getWords(), 0, false);
                if (newString != this.value)
                {                            
                    var words = newString.split(" ");
                    for (var i = 0; i < words.length; i++)
                    {
                        if (this.type == 0 && pd_getWordIndex(pd_g_sStopWords, words[i]) != -1)
                            continue;
                        var documents = pd_searchInIndex(words[i], true);
                        if (!documents) 
                        {
                            this.docs = null;
                            break;
                        }
                        this.docs = !this.docs ? documents : pd_intersect(this.docs, documents, true);
                    }
                }
            }
        }
        else
        {
            for (var i = 0; i < this.children.length; i++)
            {
                if (this.type == 0 && pd_getWordIndex(pd_g_sStopWords, this.children[i].value) != -1)
                    continue;
                if (this.children[i].execute())
                {
                    if (this.type == 0 || this.type == 2)
                        this.docs = !this.docs ? this.children[i].docs : pd_intersect(this.docs, this.children[i].docs, this.type == 0);
                    else
                        this.docs = !this.docs ? this.children[i].docs : pd_mergeDocs(this.docs, this.children[i].docs);

                }
                else if (this.type != 1 || i == 0)
                    this.docs = null;
                if (this.docs == null && this.type != 1)
                    break;                
            }
        }
        if (this.not && this.docs)
            this.docs = pd_invert(this.docs);                    
        return this.docs != null;
    },
    getQueryString:function(corrected){
        if (this.children == null)
        {
            var word;
            // if this is a probably misspelled word
            if (corrected && this.type != 0 && !pd_isWildcard(this.value) && (!this.docs || this.docs.length < 2) && !aliasesHT[this.value])
                word = pd_getSimilarWord(this.value);
            else
                word = this.value;
            return this.not && this.type != 0 ? searchNotInSpaces + word : word;
        }
        else
        {
            var query = "";
            var typeName = this.type == 1 ? searchOrInSpaces : " ";
            for (var i = 0; i < this.children.length; i++)
            {
                var subquery = this.children[i].getQueryString(corrected);
                if (i == 0)
                    query = subquery;
                else if (this.children[i].not && this.type == 2)
                    query += searchAndInSpaces + subquery;
                else
                    query += typeName + subquery;
            }
            if (this.type == 0)
            {
                query = "\"" + query + "\"";
                if (this.not)
                    query = searchNotInSpaces + query;                
            }
            return query;
        }
    }
}

var searchAnd = "AND".toUpperCase();
var searchOr = "OR".toUpperCase();
var searchNot = "NOT".toUpperCase();
var searchAndInSpaces = " " + searchAnd + " ";
var searchOrInSpaces = " " + searchOr + " ";
var searchNotInSpaces = searchNot + " ";
var d2hQuotePrefix = "_d2hQuote";
var quotes = new Array();
var aliasesHT = new Array();

function pd_getWords()
{
    var wordsSorted = new Array();
    wordsSorted.length = pd_g_sWords.length;
    for(var i = 0; i < pd_g_sWords.length; i++)
        wordsSorted[i] = pd_g_sWords[i];
    wordsSorted.sort(pd_sortByWordsLength);
    return wordsSorted;    
}

function pd_sortByWordsLength(x, y)
{
    var delta = x.length - y.length;
    if (delta < 0)
        return 1;
    if (delta > 0)        
        return -1;
    return 0;
}

function pd_getAliases(word)
{
    var indexes = aliasesHT[word];
    var words;
    if (indexes && (typeof indexes != "function"))
    {
        words = pd_g_sAliases[indexes[0]];
        for (var i = 1; i < indexes.length; i++)
            words = pd_mergeSimple(words, pd_g_sAliases[indexes[i]]);
    }
    else
    {
        words = new Array(1);
        words[0] = word;
    }
    return words;
}

function getDistance(x, y, maxDelta)
{
    if (x.charAt(0) != y.charAt(0) || Math.abs(x.length-y.length) > 2)
        return maxDelta + 1;
    var N = x.length + 1, M = y.length + 1, min;
    var a = new Array(N);
    for (var i = 0; i < N; i++)
    {
        a[i] = new Array(M);
        a[i][0] = i;
    }
    for (var i = 0; i < M; i++)
        a[0][i] = i;
    for (var i = 1; i < N; i++)
    {
        min = N+M;
        for (var j = 1; j < M; j++)
        {
            a[i][j] = Math.min(a[i-1][j-1] + (x.charAt(i-1)==y.charAt(j-1) ? 0 : 1), Math.min(a[i-1][j], a[i][j-1]) + 1);
            if (a[i][j] < min)
                min = a[i][j];
        }
        if (min > maxDelta)
            return maxDelta + 1;
    }
    return a[N-1][M-1];
}

function pd_getSimilarWord(w)
{
    if (isEasternLanguage(w))
        return w;
    var maxDelta = 3 * Math.round(0.4 + w.length / 10);
    var bestWordIndex = -1, bestLength = -1;
    for(var i = 0; i < pd_g_sWords.length; i++)
    {
        var word = pd_g_sWords[i], topicsCount = pd_getWordTopicsItemLength(i);
        if (topicsCount == 1 && !aliasesHT[word])
            continue;
        var d = getDistance(word, w, maxDelta);
        if (d > maxDelta || d == 0)
            continue;
        if (d < maxDelta || bestWordIndex == -1)
        {
            maxDelta = d;
            bestWordIndex = i;
            bestLength = topicsCount;
        }
        else if (topicsCount > bestLength)
        {
            bestWordIndex = i;
            bestLength = topicsCount;
        }
    }
    return bestWordIndex != -1 ? pd_g_sWords[bestWordIndex] : w;
}

function pd_initAliases()
{
    for (var i = 0; i < pd_g_sAliases.length; i++)
        for (var j = 0; j < pd_g_sAliases[i].length; j++)
        {
            var word = pd_g_sAliases[i][j];
            if (aliasesHT[word] == null)
                aliasesHT[word] = new Array(1);
            aliasesHT[word][aliasesHT[word].length-1] = i;
        }
}

function pd_addAND(str)
{
    for (var i = str.length - searchNotInSpaces.length - 1; i >=0; i--)
        if (str.substring(i, i + searchNotInSpaces.length + 1).toUpperCase() == " " + searchNotInSpaces)
        {                
            var startIndex = i - searchAndInSpaces.length + 1;
            var found = startIndex >= 0 ? str.substring(startIndex, i + 1).toUpperCase() == searchAndInSpaces : false;            
            if (!found)
            {
                startIndex = i - searchOrInSpaces.length + 1;
                found = startIndex >= 0 ? str.substring(startIndex, i + 1).toUpperCase() == searchOrInSpaces : false;            
            }
            if (!found)
                str = str.substring(0, i) + " " + searchAnd + str.substring(i, str.length);
        }
   return str;
}

function pd_jExecQuery(doc, strQuery)
{
    var i = 0;
    var j = 0;
    var l = -1;
    while (j < strQuery.length)    
    {
        if (strQuery.charAt(j) == '\"')
        {
            if (l == -1)
                l = j;
            else
            {
                var s = d2hQuotePrefix + i;
                quotes[s] = strQuery.substring(l+1, j);
                strQuery = strQuery.substring(0, l) + s + strQuery.substring(j+1, strQuery.length);
                i++;
                j = l + s.length;
                l = -1;
            }
        }
        j++;
    }
    for (var i = 0; i < pd_g_sStopWords.length; i++)
    {
        var word = pd_g_sStopWords[i].toUpperCase();    
        if (word == searchOr || word == searchAnd || word == searchNot)
            continue;
        var r = new RegExp("\\b" + pd_g_sStopWords[i] + "\\b", 'ig');
        strQuery = strQuery.replace(r, " ");
    }    
    strQuery = strQuery.replace(/\s+|\.|,|"|;|\(|\)|\{|\}|\[|\]/g, " ").replace(/^\s+/g, "").replace(/\s+$/g, "");    
    strQuery = pd_addAND(strQuery);
    pd_initAliases();
    var root = new pd_Clause(strQuery);
    root.execute();
    var original = root.getQueryString(false);
    var corrected = root.getQueryString(true);
    if (original == corrected)
        corrected = "";
    pd_setQuery2EditBox(original);    
    if (this.docs)
        root.docs = pd_calcHistogram(root.docs);    
	return pd_getQueryResult(doc, corrected, root.docs);    
}

function pd_getDocID(arr)
{
    return arr[0];
}

function pd_invert(docs)
{
    var docsLength = docs ? docs.length : 0;
    var cnt = pd_g_sTopics.length - docsLength;
    var newDocs = new Array(cnt);
    var j = 0, l = 0;
    var id = j < docsLength ? pd_getDocID(docs[j++]) : pd_g_sTopics.length;        
    for (var i = 0; i < pd_g_sTopics.length; i++)
    {        
        if (i < id)
        {
            newDocs[l] = new Array(1);
            newDocs[l][0] = i;
            l++;
        }
        else if (i == id)
            id = j < docsLength ? pd_getDocID(docs[j++]) : pd_g_sTopics.length;
    }
    return newDocs;
}

function pd_intersect(docs1, docs2, exactPhrase)
{
    if (!docs1  || !docs2)
        return null; 
    var docs = new Array(docs1.length);
    var i = 0, j = 0, k = 0;
    while (i < docs1.length && j < docs2.length)
    {
        var id1 = pd_getDocID(docs1[i]), id2 = pd_getDocID(docs2[j]);
        if (id1 == id2)
        {
            var p1 = 1, p2 = 1, p = 1;
            var positions = new Array();
            positions[0] = id1;
            if (exactPhrase)
            {                
                while (p1 < docs1[i].length && p2 < docs2[j].length)
                {
                    if (docs1[i][p1] == docs2[j][p2] - 1)
                    {
                        positions[p++] = docs2[j][p2];
                        p1++;
                        p2++;
                    }
                    while (p1 < docs1[i].length && docs1[i][p1] < docs2[j][p2] - 1)
                        p1++;            
                    while (p2 < docs2[j].length && docs2[j][p2] <= docs1[i][p1])
                        p2++;                            
                }
            }
            if (!exactPhrase || positions.length > 1)
            {
                docs[k] = positions;
                k++;
            }
            i++;
            j++;            
        }
        else if (id1 < id2)
        {
            while (i < docs1.length && pd_getDocID(docs1[i]) < id2)
                i++;            
        }
        else
        {
            while (j < docs2.length && pd_getDocID(docs2[j]) < id1)
                j++;
        }
    }
    if (docs.length > k)
        docs.length = k;
    return docs;
}

function pd_mergeSimple(x, y)
{
    if (x == null)
        return y;
    if (y == null)
        return x;
    var res = new Array(x.length + y.length);
    var i = 0, j = 0, k = 0;    
    while (i < x.length && j < y.length)
    {        
        if (x[i] == y[j])
        {
            res[k++] = x[i++];
            j++;        
        }
        else
            res[k++] = x[i] < y[j] ? x[i++] : y[j++];    
    }
    while (i < x.length)
        res[k++] = x[i++];
    while (j < y.length)
        res[k++] = y[j++];
    if (res.length > k)
        res.length = k;
    return res;
}

function pd_mergeDocs(x, y)
{
    if (x == null)
        return y;
    if (y == null)
        return x;
    var res = new Array(x.length + y.length);
    var i = 0, j = 0, k = 0;    
    while (i < x.length && j < y.length)
    {
        var id1 = pd_getDocID(x[i]), id2 = pd_getDocID(y[j]);
        if (id1 == id2)
        {
            res[k] = new Array(2);
            res[k++] = pd_mergeSimple(x[i++], y[j++]);
        }
        else
            res[k++] = id1 < id2 ? x[i++] : y[j++];
    }
    while (i < x.length)
        res[k++] = x[i++];
    while (j < y.length)
        res[k++] = y[j++];
    if (res.length > k)
        res.length = k;
    return res;
}

function pd_setQuery2EditBox(query)
{
    var doc = d2hGetSearchFrameDocument();
    if (doc != null)
    {
        var elmSearch = getElemById(doc, "query");
        if (elmSearch != null)
            elmSearch.value = query.replace(/\s+$/g, "");
    }
}

function pd_getWordIndex(words, word)
{
    var l = 0, r = words.length - 1;    
    while (r > l)
    {
        var m = Math.round((l + r) / 2);
        if (words[m] < word)
            l = m + 1;
        else if (words[m] > word)
            r = m - 1;
        else
            return m;
    }
    return l == r && words[l] == word ? l : -1;
}

function pd_getWordTopicsItemLength(i)
{
    var j = 0, k = 0;
    while (j < pd_g_sWordTopics[i].length)
    {
        k++;
        j+=pd_g_sWordTopics[i][j+1]+2;
    }
    return k;
}

function pd_getWordTopicsItem(i)
{
    var arr = new Array(pd_getWordTopicsItemLength(i));
    var j = 0, k = 0;
    while (j < pd_g_sWordTopics[i].length)
    {    
        arr[k] = new Array(pd_g_sWordTopics[i][j+1]+1);
        arr[k][0] = pd_g_sWordTopics[i][j];
        for (var l = 0; l < pd_g_sWordTopics[i][j+1]; l++)
            arr[k][l+1] = pd_g_sWordTopics[i][j+2+l];
        k++;
        j+=pd_g_sWordTopics[i][j+1]+2;
    }
    return arr;
}

function pd_searchInIndex(term, allowWildcards)
{
    var wildcard = allowWildcards && pd_isWildcard(term);
    if (wildcard)
    {
        var re = new RegExp(getWildcardRegexp(term), "gi");
        var indx;
        var res = null;
        for(var i = 0; i < pd_g_sWords.length; i++)
        {
            indx = pd_g_sWords[i].search(re);
            if (indx > -1)
            {
                if (res)
                    res = pd_mergeDocs(res, pd_getWordTopicsItem(i));
                else
                    res = pd_getWordTopicsItem(i);
            }
        }
        return res;
    }
    else
    {
        var index = pd_getWordIndex(pd_g_sWords, term);
        return index != -1 ? pd_getWordTopicsItem(index) : null;
    }
}

function pd_getWordsFromIndex(termWithWildcards)
{
    var words = new Array();
    var re = new RegExp(getWildcardRegexp(termWithWildcards), "gi");
    for(var i = 0; i < pd_g_sWords.length; i++)
        if (pd_g_sWords[i].search(re) > -1)
        {
            words.length = words.length + 1;
            words[words.length - 1] = pd_g_sWords[i];
        }
    return words;
}

function pd_isWildcard(term)
{
    return term.indexOf("?") > -1 || term.indexOf("*") > -1;
}

function pd_removeRepeatingTerms(terms)
{
    var htbl = new Array();
    var res = new Array();
    for (var i = 0; i < terms.length; i++)
        if (!htbl[terms[i]])
        {
            res[res.length] = terms[i];
            htbl[terms[i]] = true;
        }
    return res;
}

function pd_calcHistogram(arr)
{
    var tbl = new Array();
    var id;
    for (var i = 0; i < arr.length; i++)
    {
        id = "x" + arr[i][0];
        if (tbl[id])
        {
            tbl[id] = new Array(tbl[id].length + arr[i].length);
            arr[i] = null;
        }
        else
            tbl[id] = arr[i];
    }
    arr.sort(pd_sortByCounterNumber);
    return arr;
}

function pd_sortByCounterNumber(x, y)
{
    if (x == null)
        return 1;
    if (y == null)
        return -1;
    var delta = x.length - y.length;
    if (delta == 0)
    {
        var xTopic = pd_g_sTopics[x[0]][1];
        var yTopic = pd_g_sTopics[y[0]][1];
        if (xTopic > yTopic)
            return 1;
        else if (xTopic < yTopic)
            return -1;            
        return 0;
    }
    if (delta < 0)
        return 1;
    return -1;
}

function pd_getQueryResult(doc, newQuery, arr)
{
    var res = "";
    if (arr != null)
        for (var i = 0; i < arr.length; i++)
            if (arr[i])
                res += pf_getResultItem(i, arr[i]);
    if (res.length == 0)
        res = "No topics found.";
    if (newQuery.length > 0)
        res = "<div class=\"clsSearchDidYouMean\">Did you mean: <a class=\"clsMouseOver\" href=\"" + doc.location.href.replace(doc.location.search, "?query=" + encodeURI(newQuery)) + "\">" + newQuery + "</a></div><br>" + res;
    return res;
}

function pf_getResultItem(id, item)
{
    var res = "";
    try
    {
        var td = pd_g_sTopics[item[0]];
        if (td)
        {
            res = "<div nowrap class=\"clsSearchResultItem\"><a id=\"ri-" + id + "\" href=\"";
            res += td[0];

            res += "\" target=\"right\"";

            res += "onclick=\"return d2hSearchItemSelect(\'" + td[0] + "\', event)\" onmouseover=\"d2hItemOver(event)\" onmouseout=\"d2hItemOut(event)\">";
            res += td[1] + "</a></div>";
        }
    }
    catch (e)
    {
        res = "";
    }
    return res;
}

function pd_display(doc, content)
{
    var elem = doc.forms[1];
    if (elem.parentNode)
        elem.parentNode.setAttribute("nowrap", true);
    pd_waitCursor(doc, true);
    elem.innerHTML = content;
    pd_waitCursor(doc, false);
}

function pd_waitCursor(doc, isWait)
{
}
