<script lang="ts">

    import {CircleUserRound} from '@lucide/svelte'
    import {ArrowLeft} from "@lucide/svelte"
    import {goto} from "$app/navigation";
    import {page} from "$app/state";

    const username = page.params.username
    let loading = false /*todo change to true once fetch_data is used*/
    let error = false

    type User = {
        username : string
    }

    let following : User [] = [{username:"zebraschmetterling2"}, {username: "tigerente15"}, {username: "benbeispiel"}]

    function navProfile () {
        goto(`/${username}`)
    }

    async function fetch_data(){
        const response = await fetch("http://localhost:8080/") /*todo adjust url*/
        if(!response.ok) {
            console.error("Failed to fetch data")
            error = true
            loading = false
        }
        const data  = await response.json()
        /* todo process data:
        following = */
    }

</script>

<div class="back">
    <button type="button" on:click={navProfile}>
        <ArrowLeft />
    </button>
</div>
<h2 class="header"> Following </h2>
{#if loading}
    <br> <br>
    <h3 class="loading"> Loading ... </h3>
{:else if error}
    <br> <br>
    <h3 class="error"> An error occurred. Please try again. </h3>
{:else}
    {#each following as user}
        <a href="/{user.username}" class="link">
            <div class="user">
                <div class="user-icon">
                    <CircleUserRound />
                </div>
                <div class="user-info">
                    <b>{user.username}</b>
                </div>
            </div>
        </a>
        <br>
    {/each}
{/if}

<style>
    .back {
        display: flex;
        width: 40px;
    }
    .header {

        text-align: center;
    }
    .user {
        display: flex;
        padding-bottom: 5px;
    }
    .user-icon {
        width: 50px;
        display: inline-block;
        padding-left: 10px;
    }
    .user-info {
        display: inline-block;
        text-align: center;
        padding-top: 4px;
    }
    .link {
        display: inline-block;
    }

</style>