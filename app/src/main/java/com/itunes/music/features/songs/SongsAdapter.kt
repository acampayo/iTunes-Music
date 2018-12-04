package com.itunes.music.features.songs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.itunes.music.R
import kotlinx.android.synthetic.main.adapter_songs.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

class SongsAdapter
@Inject constructor() : BaseAdapter() {

    internal var songs: List<Song> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder
        val song = getItem(position)
        val inflater: LayoutInflater = parent.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) {
            view = inflater.inflate(R.layout.adapter_songs, parent, false)

            holder = ViewHolder()
            holder.cover = view.cover
            holder.title = view.title
            holder.artist = view.artist
            holder.album = view.album
            holder.releaseDate = view.releaseDate
            holder.length = view.length
            holder.genre = view.genre
            holder.price = view.price

            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val cover = holder.cover
        val title = holder.title
        val artist = holder.artist
        val album = holder.album
        val releaseDate = holder.releaseDate
        val length = holder.length
        val genre = holder.genre
        val price = holder.price

        title.text = song.trackName
        artist.text = song.artistName
        album.text = song.collectionName
        releaseDate.text = song.formattedReleaseDate
        length.text = song.formattedLength
        genre.text = song.primaryGenreName
        price.text = song.formattedPrice

        Glide.with(parent.context.applicationContext)
                .load(song.artworkUrl100)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(cover)

        return view
    }

    override fun getItem(position: Int) = songs[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = songs.size

    private class ViewHolder {
        lateinit var cover: ImageView
        lateinit var title: TextView
        lateinit var artist: TextView
        lateinit var album: TextView
        lateinit var releaseDate: TextView
        lateinit var length: TextView
        lateinit var genre: TextView
        lateinit var price: TextView
    }
}