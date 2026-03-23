<script lang="ts">

    import {CircleUserRound} from '@lucide/svelte'
    import {ArrowLeft} from "@lucide/svelte"
    import {goto} from "$app/navigation";
    import {page} from "$app/state";

    const username = page.params.username


    type User = {
        username : string
    }

    let following : User [] = [{username:"zebraschmetterling2"}, {username: "tigerente15"}, {username: "benbeispiel"}]

    function navProfile () {
        goto(`/${username}`)
    }

</script>

<div class="back">
    <button type="button" on:click={navProfile}>
        <ArrowLeft />
    </button>
</div>
<h2 class="header"> Following </h2>
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