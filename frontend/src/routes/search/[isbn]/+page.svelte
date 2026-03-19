<script lang="ts">

    import NavigationBar from "$lib/NavigationBar.svelte";
    import { page } from "\$app/state";
    import {onMount} from "svelte";

    const isbn = page.params.isbn

    let loading : boolean = false
    let error : boolean = false
    let book : Book

    type Book = {
        title : string
        subtitle? : string
        authors : string
        publishedDate : string
        pageCount : string
        language : string
        description? : string
        thumbnail : string
    } | null

    type Item = {
        volumeInfo : {
            title : string
            subtitle? : string
            authors : string[]
            publishedDate : string
            description? : string
            industryIdentifiers : {
                type : string
                identifier : string
            } []
            pageCount : string
            imageLinks : {
                thumbnail : string
            }
            language : string
        }
    }

    async function fetch_data(){
        const response = await fetch (`https://www.googleapis.com/books/v1/volumes?q=isbn:${isbn}`)//(`http://localhost:8080/search-isbn/${isbn}`)
        if(!response.ok) {
            console.error("Failed to fetch data")
            error = true
        }
        const data = await response.json()
        const first : Item = data.items?.[0]
        book = first ? {
            title : first.volumeInfo.title,
            subtitle : first.volumeInfo.subtitle,
            authors : first.volumeInfo.authors.join(", "),
            publishedDate : first.volumeInfo.publishedDate,
            pageCount : first.volumeInfo.pageCount,
            language : first.volumeInfo.language,
            description : first.volumeInfo.description,
            thumbnail : first.volumeInfo.imageLinks.thumbnail

        } : null

        loading = false
    }

    function decodeLanguageCode(code:string) {
        const lang : Record <string, string>= {
            aa: "Afar",
            ab: "Abkhazian",
            ae: "Avestan",
            af: "Afrikaans",
            ak: "Akan",
            am: "Amharic",
            an: "Aragonese",
            ar: "Arabic",
            as: "Assamese",
            av: "Avaric",
            ay: "Aymara",
            az: "Azerbaijani",
            ba: "Bashkir",
            be: "Belarusian",
            bg: "Bulgarian",
            bi: "Bislama",
            bm: "Bambara",
            bn: "Bengali",
            bo: "Tibetan",
            br: "Breton",
            bs: "Bosnian",
            ca: "Catalan",
            ce: "Chechen",
            ch: "Chamorro",
            co: "Corsican",
            cr: "Cree",
            cs: "Czech",
            cu: "Church Slavic",
            cv: "Chuvash",
            cy: "Welsh",
            da: "Danish",
            de: "German",
            dv: "Dhivehi",
            dz: "Dzongkha",
            ee: "Ewe",
            el: "Greek",
            en: "English",
            eo: "Esperanto",
            es: "Spanish",
            et: "Estonian",
            eu: "Basque",
            fa: "Persian",
            ff: "Fulah",
            fi: "Finnish",
            fj: "Fijian",
            fo: "Faroese",
            fr: "French",
            fy: "Western Frisian",
            ga: "Irish",
            gd: "Gaelic",
            gl: "Galician",
            gn: "Guarani",
            gu: "Gujarati",
            gv: "Manx",
            ha: "Hausa",
            he: "Hebrew",
            hi: "Hindi",
            ho: "Hiri Motu",
            hr: "Croatian",
            ht: "Haitian",
            hu: "Hungarian",
            hy: "Armenian",
            hz: "Herero",
            ia: "Interlingua",
            id: "Indonesian",
            ie: "Interlingue",
            ig: "Igbo",
            ii: "Sichuan Yi",
            ik: "Inupiaq",
            io: "Ido",
            is: "Icelandic",
            it: "Italian",
            iu: "Inuktitut",
            ja: "Japanese",
            jv: "Javanese",
            ka: "Georgian",
            kg: "Kongo",
            ki: "Kikuyu",
            kj: "Kuanyama",
            kk: "Kazakh",
            kl: "Kalaallisut",
            km: "Central Khmer",
            kn: "Kannada",
            ko: "Korean",
            kr: "Kanuri",
            ks: "Kashmiri",
            ku: "Kurdish",
            kv: "Komi",
            kw: "Cornish",
            ky: "Kirghiz",
            la: "Latin",
            lb: "Luxembourgish",
            lg: "Ganda",
            li: "Limburgan",
            ln: "Lingala",
            lo: "Lao",
            lt: "Lithuanian",
            lu: "Luba-Katanga",
            lv: "Latvian",
            mg: "Malagasy",
            mh: "Marshallese",
            mi: "Maori",
            mk: "Macedonian",
            ml: "Malayalam",
            mn: "Mongolian",
            mr: "Marathi",
            ms: "Malay",
            mt: "Maltese",
            my: "Burmese",
            na: "Nauru",
            nb: "Norwegian Bokmål",
            nd: "Ndebele North",
            ne: "Nepali",
            ng: "Ndonga",
            nl: "Dutch",
            nn: "Norwegian Nynorsk",
            no: "Norwegian",
            nr: "Ndebele South",
            nv: "Navajo",
            ny: "Chichewa",
            oc: "Occitan",
            oj: "Ojibwa",
            om: "Oromo",
            or: "Oriya",
            os: "Ossetian",
            pa: "Panjabi",
            pi: "Pali",
            pl: "Polish",
            ps: "Pushto",
            pt: "Portuguese",
            qu: "Quechua",
            rm: "Romansh",
            rn: "Rundi",
            ro: "Romanian",
            ru: "Russian",
            rw: "Kinyarwanda",
            sa: "Sanskrit",
            sc: "Sardinian",
            sd: "Sindhi",
            se: "Northern Sami",
            sg: "Sango",
            si: "Sinhala",
            sk: "Slovak",
            sl: "Slovenian",
            sm: "Samoan",
            sn: "Shona",
            so: "Somali",
            sq: "Albanian",
            sr: "Serbian",
            ss: "Swati",
            st: "Sotho Southern",
            su: "Sundanese",
            sv: "Swedish",
            sw: "Swahili",
            ta: "Tamil",
            te: "Telugu",
            tg: "Tajik",
            th: "Thai",
            ti: "Tigrinya",
            tk: "Turkmen",
            tl: "Tagalog",
            tn: "Tswana",
            to: "Tonga",
            tr: "Turkish",
            ts: "Tsonga",
            tt: "Tatar",
            tw: "Twi",
            ty: "Tahitian",
            ug: "Uighur",
            uk: "Ukrainian",
            ur: "Urdu",
            uz: "Uzbek",
            ve: "Venda",
            vi: "Vietnamese",
            vo: "Volapük",
            wa: "Walloon",
            wo: "Wolof",
            xh: "Xhosa",
            yi: "Yiddish",
            yo: "Yoruba",
            za: "Zhuang",
            zh: "Chinese",
            zu: "Zulu"
        }

        return lang[code] ?? code
    }

    onMount(fetch_data)

</script>
<div>
    {#if loading}
        <h3> Loading ... </h3>
    {:else if error}
        <h3> An error occurred. Please try again. </h3>
    {:else}
        {#if book}
            <img src={book.thumbnail} alt="Book Cover"/>
            <h2> {book.title} </h2>
            {#if book.subtitle} <h3> {book.subtitle}</h3>{/if}

            <h3> {book.authors} </h3>

            <br>

            <h4>More Infos:</h4>

            <b>Page count :</b> {book.pageCount} <br>
            <b>Language :</b> {decodeLanguageCode(book.language)} <br>
            <b>Published date :</b> {book.publishedDate} <br>
            {#if book.description}<b>Description : </b> {book.description}<br> {/if}

        {:else} <h3> Book could not be found</h3>
        {/if}
    {/if}

</div>



<br> <br>
<NavigationBar/>