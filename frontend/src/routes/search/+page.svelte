<script lang="ts">

    import NavigationBar from "$lib/NavigationBar.svelte";

    type Book = {
        title : string
        subtitle? : string
        authors : string
        isbn : string
    }

    type Item = {
        volumeInfo : {
            title : string
            subtitle? : string
            authors : string[]
            industryIdentifiers : {
                type : string
                identifier : string
            } []
        }
    }

    let search_q = ""
    let loading : boolean = false
    let searched : boolean = false
    let error : boolean = false
    let books : Book[] = []

    async function fetch_data () {
        searched = true
        loading = true
        search_q.replaceAll("\\s", "+")
        const response = await fetch (`https://www.googleapis.com/books/v1/volumes?q=${search_q}`)//(`http://localhost:8080/search/${search_q}`)
        if(!response.ok) {
            console.error("Failed to fetch data")
            error = true
        }
        const data  = await response.json()
        books = (data.items ?? []).map((item:Item)=> ({
            title : item.volumeInfo.title,
            subtitle : item.volumeInfo.subtitle,
            authors : item.volumeInfo.authors.join(", "),
            isbn : item.volumeInfo.industryIdentifiers.find(id => id.type === 'ISBN_13')?.identifier.replaceAll("\\s", "")
        }))

        loading = false
    }


</script>

<h1> Search</h1>
<form on:submit|preventDefault={fetch_data}>
    <input
            name="searchbar"
            type="text"
            placeholder="Search your book"
            bind:value={search_q}
    />
    <input
            type="submit"
            value="Search"
    />

</form>
<div>
    {#if searched }
        {#if loading}
            <h5> Loading ... </h5>
        {:else if error}
            <h5> An error occurred. Please try again. </h5>
        {:else}
            {#each books as book}
                <a href="http://localhost:5173/search/{book.isbn}">
                    <b>{book.title}</b>
                </a> {#if book.subtitle} – {book.subtitle} {/if}
                <br>
                {book.authors}
                <br><br>
            {/each}

        {/if}
    {/if}
</div>


<br><br>
<NavigationBar/>