<script lang="ts">

    import NavigationBar from "$lib/NavigationBar.svelte";
    import type {Actions} from "@sveltejs/kit";

    async function fetch_data (q:string) {
        await fetch ("http://localhost:8080/search")
    }

    export const actions = {
        search: async (event) => {
            await fetch("http://localhost:8080/search")
        }
    } satisfies Actions

</script>

<h1> Search</h1>
<form method="POST" action="/search">
    <input
            name="searchbar"
            type="text"
            placeholder="Search your book"
    />

</form>
<br>



<NavigationBar/>