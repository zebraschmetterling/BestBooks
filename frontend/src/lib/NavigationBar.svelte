<script lang="ts">

    import {Search} from '@lucide/svelte'
    import {CircleUserRound} from '@lucide/svelte'
    import {House} from '@lucide/svelte'
    import {LogIn} from "@lucide/svelte";
    import {goto} from "$app/navigation";

    function navHome(){
        goto("/")
    }

    function navSearch() {
        goto("/search")
    }

    function navProfile() {
        goto("/profile")
    }

    function navLogin() {
        goto("/login")
    }


</script>

<button type="button" on:click={navHome}>
    <House/>
</button>
<button type="button" on:click={navSearch}>
    <Search/>
</button>
<button type="button" on:click={navProfile}>
    <CircleUserRound />
</button>
<button type="button" on:click={navLogin}>
    <LogIn />
</button>



